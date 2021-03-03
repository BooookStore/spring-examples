package com.example.spring.security.oauth2.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    private final OAuth2AuthorizedClientService authorizedClientService;

    public HomeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
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

}
