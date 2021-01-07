Spring Security Simple
===

Spring Security を使用してユーザー管理を行うウェブアプリケーションです。

# 起動方法

プロジェクトをクローンし、 `spring-security-simple` ディレクトリで以下のコマンドより起動します。

``` bash
gradle build
docker-compose up
```

# ログイン情報

デフォルトで以下のユーザーが設定されています。

|ユーザー名|パスワード|ロール|
|:---|:---|:---|
|bob|12345|ADMIN
|alice|12345|NORMAL|

サインアップページから新しいユーザーを作成することもできます。作成されたユーザーのデフォルトの権限はNORMALになります。

## ロール

ロールは以下の２つがあります。

* ADMIN ... 管理ロール。管理ページにアクセスできる
* NORMAL ... 一般ロール。