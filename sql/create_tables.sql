-- =========================
-- ユーザー情報テーブル（ログイン用デモのため平文保存）
-- =========================
CREATE TABLE public.m_user (
  user_id     INTEGER NOT NULL,
  user_pass   INTEGER,
  user_name   VARCHAR(10),
  PRIMARY KEY (user_id)
);

-- =========================
-- カード情報テーブル
-- =========================
CREATE TABLE public.ptcg_cards (
  card_id            INTEGER NOT NULL,
  card_name          VARCHAR(24) NOT NULL,
  category_id        INTEGER,
  expansion_code     VARCHAR(8),
  image_file_name    VARCHAR(128),
  registration_time  TIMESTAMP(6) WITHOUT TIME ZONE,
  update_time        TIMESTAMP(6) WITHOUT TIME ZONE,
  price              INTEGER,
  PRIMARY KEY (card_id)
);

-- =========================
-- カテゴリ情報テーブル
-- =========================
CREATE TABLE public.ptcg_categories (
  category_id    INTEGER NOT NULL,
  category_name  VARCHAR(16) NOT NULL,
  PRIMARY KEY (category_id)
);

-- =========================
-- エキスパンション情報テーブル
-- =========================
CREATE TABLE public.ptcg_expansions (
  expansion_code  VARCHAR(8) NOT NULL,
  expansion_name  VARCHAR(16) NOT NULL,
  release_date    DATE,
  PRIMARY KEY (expansion_code)
);

-- =========================
-- サンプルデータ INSERT
-- =========================

-- m_user
INSERT INTO public.m_user (user_id, user_pass, user_name) VALUES
    (1,1,'ゲスト1')
  , (2,22,'ゲスト2')
  , (3,333,'ゲスト3');

-- ptcg_categories
INSERT INTO public.ptcg_categories (category_id, category_name) VALUES
    (0,'ポケモン')
  , (1,'グッズ')
  , (2,'ポケモンのどうぐ')
  , (3,'サポート')
  , (4,'スタジアム')
  , (5,'エネルギー');

-- ptcg_expansions
INSERT INTO public.ptcg_expansions (expansion_code, expansion_name, release_date) VALUES
    ('sv1S','スカーレットex',DATE '2023-01-20')
  , ('sv1V','バイオレットex',DATE '2023-01-20')
  , ('sv1a','トリプレットビート',DATE '2023-03-10')
  , ('sv2D','クレイバースト',DATE '2023-04-14')
  , ('sv2P','スノーハザード',DATE '2023-04-14')
  , ('sv2a','イチゴーイチ',DATE '2023-06-16')
  , ('sv3','黒炎の支配者',DATE '2023-07-28')
  , ('sv3a','レイジングサーフ',DATE '2023-09-22')
  , ('sv4K','古代の咆哮',DATE '2023-10-27')
  , ('sv4M','未来の一閃',DATE '2023-10-27')
  , ('sv4a','シャイニートレジャーex',DATE '2023-12-01')
  , ('sv5K','サイバージャッジ',DATE '2024-01-26')
  , ('sv5M','ワイルドフォース',DATE '2024-01-26')
  , ('sv5a','クリムゾンヘイズ',DATE '2024-03-22')
  , ('sv6','変幻の仮面',DATE '2024-04-26')
  , ('sv6a','ナイトワンダラー',DATE '2024-06-07')
  , ('sv7','ステラミラクル',DATE '2024-07-19')
  , ('sv7a','楽園ドラゴーナ',DATE '2024-09-17')
  , ('sv8','超電ブレイカー',DATE '2024-10-18')
  , ('sv8','テラスタルフェスex',DATE '2024-12-06')
  , ('sv9','バトルパートナーズ',DATE '2025-01-24')
  , ('svABC','テストABC',DATE '2025-02-20')
  , ('sv9a','熱風のアリーナ',DATE '2025-03-14')
  , ('sv00','架空のパック',DATE '2025-04-01')
  , ('sv10','ロケット団の栄光',DATE '2025-04-18');

-- ptcg_cards
INSERT INTO public.ptcg_cards (card_id, card_name, category_id, expansion_code, image_file_name, registration_time, update_time, price) VALUES
    (0,'ピカチュウ',0,'sv2a','pikachu.png', now(), now(),80)
  , (1,'モンスターボール',1,'svM','pokeball.png', now(), now(),30)
  , (2,'まけんきハチマキ',2,'sv1S','defiance_band.png', now(), now(),280)
  , (3,'博士の研究',3,'sv4a','professor.png', now(), now(),20)
  , (4,'エキサイトスタジアム',4,'sv8','stadium.png', now(), now(),80)
  , (5,'ジェットエネルギー(UR)',5,'sv7a','energy.png', now(), now(),1600)
  , (6,'リザードンex',0,'sv3','charizard_ex.png', now(), now(),330);
