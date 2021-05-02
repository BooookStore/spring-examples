package com.example.spring.security.oauth2.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final WebClient webClient;

    public HomeController(OAuth2AuthorizedClientService authorizedClientService, WebClient webClient) {
        this.authorizedClientService = authorizedClientService;
        this.webClient = webClient;
    }

    @GetMapping
    public String home(Authentication authentication, Model model) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                "spring-oauth2-client-demo",
                authentication.getName()
        );
        model.addAttribute("authorized", authorizedClient != null);

        if (authorizedClient != null) {
            logger.info("access token value [{}]", authorizedClient.getAccessToken().getTokenValue());
        }

        return "home";
    }

    @GetMapping("request")
    public String requestToResourceServer(@RegisteredOAuth2AuthorizedClient("spring-oauth2-client-demo") OAuth2AuthorizedClient authorizedClient) {
        String body = webClient
                .get()
                .uri("http://localhost:8082/getDocument")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        logger.info("retrieve from resource server [{}]", body);

        return "home";
    }

}
