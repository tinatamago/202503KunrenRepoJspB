# 202503KunrenRepoJspB

職業訓練中に制作したJSP・Servlet・PostgreSQLを用いたWebアプリケーションです。

## 📌 概要

このアプリケーションは、以下の機能を備えた学習用のポートフォリオです：

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

your-project/ 
├── src/ # Javaソースコード（Servlet） 
├── WebContent/ # JSPファイル、HTML、CSSなど 
├── lib/ # JDBCドライバ等の外部ライブラリ（※Git管理対象外にする推奨） 
├── .gitignore 
└── README.md

## 🔧 セットアップ手順（開発環境）

1. 本リポジトリをクローン
2. EclipseなどのIDEでインポート
3. PostgreSQLを起動し、必要なDB・テーブルを作成
4. `lib/` 以下にJDBCドライバ（例：postgresql-42.7.3.jar）を配置
5. Apache Tomcatでデプロイして動作確認

※ `lib/` フォルダ内のJARは `.gitignore` により除外されています。手動で追加してください。

