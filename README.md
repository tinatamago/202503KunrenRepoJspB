# 202503KunrenRepoJspB

職業訓練中に制作したJSP・Servlet・PostgreSQLを用いたWebアプリケーションです。

## 📌 概要

このアプリケーションは、以下の機能を備えた学習用のポートフォリオです。

- データベースとの連携（PostgreSQL）
- 登録・更新・削除・検索といった基本的なCRUD操作
- JSPとServletによるWeb画面表示
- MVCアーキテクチャに基づく構成

## ⚠️ 注意事項

本リポジトリには、訓練校で用意されたテンプレートや教材ソースコードが一部含まれています。  
その上で、下記の機能について自身で設計・実装を行いました。

- サーブレットによる処理の実装（例：データ登録・検索など）
- JSPの画面設計と連携（JSP、HTML、CSS、画像を含む）
- PostgreSQLとの接続処理
- git/GitHubでのバージョン管理と公開

※ Ptcgという名称がつくファイルおよび、ptcgという名称のディレクトリ配下にあるファイル群が自身で作成したものとなります。

- [src/dao/PtcgDao.java](./src/dao/PtcgDao.java)
- [src/dto/PtcgDto.java](./src/dto/PtcgDto.java)
- [src/pack/ptcg](./src/pack/ptcg)
- [WebContent/js/ptcg.js](./WebContent/js/ptcg.js)
- [WebContent/css/ptcg](./WebContent/css/ptcg)
- [WebContent/jsp/ptcg](./WebContent/jsp/ptcg)
- [WebContent/upload/ptcg](./WebContent/upload/ptcg)

本リポジトリ内で使用している一部の画像は、ポケモンカード公式サイト（https://www.pokemon-card.com/）を参考にしています。

これらの画像は**非営利**に限って使用しています。  
画像の著作権は、株式会社ポケモン、および関連各社にすべて帰属します。  
万が一、権利者様からの申し出があった場合には、速やかに画像の差し替え・削除等の対応を行います。

--

## 🎥 デモ動画（YouTube）

実際の動作を以下の動画でご覧いただけます（※YouTube限定公開）※近日公開

## 🛠 使用技術

- Java（Servlet）
- JSP
- PostgreSQL
- JDBC

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
│       └── lib/           # 外部JARライブラリ（例：JDBCドライバ）※Git管理外推奨
│   └── upload/
│       └── ptcg/          # 画像の保存場所
├── sql/            # sqlファイル
⋮
```
## 🔧 セットアップ手順（開発環境）

1. 本リポジトリをクローン
2. EclipseなどのIDEでインポート
3. `lib/` 直下に下記JDBCドライバ等の外部JARを配置

| ライブラリ名 | 説明 | ダウンロードURL |
|--------------|------|------------------|
| PostgreSQL JDBC Driver | PostgreSQLとJavaを接続するためのJDBCドライバ | [ダウンロード](https://jdbc.postgresql.org/download/) |
| taglibs-standard-impl 1.2.5 | JSTLの実装ライブラリ | [Maven Central](https://repo1.maven.org/maven2/org/apache/taglibs/taglibs-standard-impl/1.2.5/) |
| taglibs-standard-jstlel 1.2.5 | JSTLのEL対応ライブラリ | [Maven Central](https://repo1.maven.org/maven2/org/apache/taglibs/taglibs-standard-jstlel/1.2.5/) |
| taglibs-standard-spec 1.2.5 | JSTLの仕様定義ライブラリ | [Maven Central](https://repo1.maven.org/maven2/org/apache/taglibs/taglibs-standard-spec/1.2.5/) |

   ※ `lib/` フォルダ内のJARは `.gitignore` により除外されています。手動で追加してください。

4. Apache Tomcatでデプロイして動作確認（Loginクラスから実行する必要があります。）
   ※ このプログラムは Tomcat（Java 17 対応） を前提としています。JDK バージョンも 17 を推奨。

## 🗄️ セットアップ手順（PostgreSQL）

1. PostgreSQL に以下の設定で接続してください：

- サーバー名：`localhost`
- データベース名：`postgres`
- ユーザー名：`postgres`
- パスワード：`postgres`

2. 以下の4つのテーブルを作成する必要があります：

- `m_user`
- `ptcg_cards`
- `ptcg_categories`
- `ptcg_expansions`

3. テーブル作成用SQLは以下にまとめています

👉 [sql/create_tables.sql](./sql/create_tables.sql)

4. 不要になったテーブルを削除する場合はこちら

👉 [sql/drop_tables.sql](./sql/drop_tables.sql)
