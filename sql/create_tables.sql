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
(1, 1, 'たろう'),
(2, 22, 'じろう'),
(3, 333, 'さぶろう'),
(4, 4444, 'しろう'),
(5, 55555, 'ごろう');

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
('sv1a','トリプレットビート',DATE '2024-03-10'),
('sv1S','スカーレットex',DATE '2024-01-20'),
('sv1V','バイオレットex',DATE '2024-01-20'),
('sv2a','イチゴーイチ',DATE '2024-06-16'),
('sv2D','クレイバースト',DATE '2024-04-14'),
('sv2P','スノーハザード',DATE '2024-04-14');

-- ptcg_cards
INSERT INTO public.ptcg_cards (card_id, card_name, category_id, expansion_code, image_file_name, registration_time, update_time, price) VALUES
    (0,'ピカチュウ',0,'sv2a','pikachu.jpg', now(), now(),200)
  , (1,'モンスターボール',1,'sv1S','pokeball.jpg', now(), now(),100)
  , (2,'げんきのハチマキ',2,'sv1V','card_image_00002.png', now(), now(),480)
  , (3,'博士の研究',3,'sv1a','professor.jpg', now(), now(),20)
  , (4,'エキサイトスタジアム',4,'sv2P','stadium.png', now(), now(),80)
  , (5,'ジェットエネルギー',5,'sv2P','energy.png', now(), now(),null)
  , (6,'リザードン',0,'sv2a','charizard.png', now(), now(),550);
