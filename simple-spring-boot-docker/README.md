# How to run

1. リポジトリをクローンします
2. プロジェクトをビルドします

```
./gradlew simple-spring-boot-docker:build
```

3. DockerfileをビルドしてDockerイメージを作成します

```
cd simple-spring-boot-docker
docker build -t spring-examples/simple-spring-boot-docker .
```

4. Dockerコンテナを起動します

```
docker run -p 8080:8080 spring-examples/simple-spring-boot-docker
```
