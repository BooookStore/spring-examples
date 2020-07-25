# How to run

1. リポジトリをクローンします
2. DockerfileをビルドしてDockerイメージを作成します。

```
docker build -t spring-examples/spring-boot-docker-multi-stage-build -f spring-boot-docker-multi-stage-build/Dockerfile .
```

3. Dockerコンテナを起動します

```
docker run -p 8080:8080 spring-examples/spring-boot-docker-multi-stage-build  
```