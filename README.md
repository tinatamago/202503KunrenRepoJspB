# 202503KunrenRepoJspB

職業訓練中に制作したJSP・Servlet・PostgreSQLを用いたWebアプリケーションです。

## 📌 概要

このアプリケーションは、以下の機能を備えた学習用のポートフォリオです。

- データベースとの連携（PostgreSQL）
- 登録・更新・削除・検索といった基本的なCRUD操作
- JSPとServletによるWeb画面表示
- MVCアーキテクチャに基づく構成

## 🛠 使用技術

- Java（Servlet）
- JSP
- PostgreSQL
- JDBC
- Eclipse
- Apache Tomcat

## 📁 構成

```
202503KunrenRepoJspB/
├── src/                   # Java各クラスのソースコード（Servletなど）
│   ├── cm/                # 共通クラス
│   ├── cnst/              # 定数クラス
│   ├── dao/               # Data Access Object クラス
│   ├── dto/               # Data Transfer Object クラス
│   └── pack/
│      ├── login/
│      │   └── Login.java  # ここから実行する必要があります。
│      └── ptcg/           # 自作サーブレットクラス
├── WebContent/            # JSP、HTML、CSS、画像など
│   ├── jsp/               # JSPファイル
│   ├── css/               # CSSファイル
│   └── WEB-INF/           # web.xmlなど設定ファイル
⋮       └── lib/           # 外部JARライブラリ（例：JDBCドライバ）※Git管理外推奨
```
## 🔧 セットアップ手順（開発環境）

1. 本リポジトリをクローン
2. EclipseなどのIDEでインポート
3. `lib/` 以下にJDBCドライバ（例：postgresql-42.7.3.jar）を配置
4. Apache Tomcatでデプロイして動作確認（Loginクラスから実行する必要があります。）

※ `lib/` フォルダ内のJARは `.gitignore` により除外されています。手動で追加してください。

## 🛠 セットアップ手順（PostgreSQL）

1. PostgreSQL に以下の設定で接続してください：

   - サーバー名：`localhost`
   - データベース名：`postgres`
   - ユーザー名：`postgres`
   - パスワード：`postgres`

2. データベースにテーブルを作成する必要があります。以下の4テーブルが必要です：

   - `m_user`
   - `ptcg_cards`
   - `ptcg_categories`
   - `ptcg_expansions`

3. `sql/create_tables.sql` に必要なテーブル作成用SQL文をまとめています。  
   → PostgreSQLでこのSQLを実行してください。

---

## 📂 SQLファイルについて

テーブル定義は `sql/create_tables.sql` に記載しています。  
データベースを構築する際にご利用ください。

## ⚠️ 注意事項

本リポジトリには、訓練校で用意されたテンプレートや教材ソースコードが一部含まれています。  
その上で、下記の機能については自身で設計・実装を行いました。
※各 ptcg/配下にあるファイル群が自身で作成したものとなります。

- サーブレットによる処理の実装（例：データ登録・検索など）
- JSPの画面設計と連携（JSP、HTML、CSS、画像を含む）
- PostgreSQLとの接続処理
- git/GitHubでのバージョン管理と公開

- このプログラムは、**Tomcat（Java 17対応）** での実行を前提としています。  
  特に `Login.java`（ログイン処理用クラス）からの実行が必要です。  
  環境構築の際は、JDK 17 および Tomcat の対応バージョンをご確認ください。



