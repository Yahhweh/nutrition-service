CREATE TABLE targets (
    id            BIGSERIAL PRIMARY KEY,
    protein       INTEGER NOT NULL,
    fat           INTEGER NOT NULL,
    carbohydrates INTEGER NOT NULL,
    ccal          INTEGER          NOT NULL,
    type          VARCHAR(32)      NOT NULL
);

CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    telegram_id BIGINT       NOT NULL UNIQUE,
    username    VARCHAR(255),
    weight      DOUBLE PRECISION,
    height      INTEGER NOT NULL ,
    sex VARCHAR(32) NOT NULL,
    age INTEGER NOT NULL ,
    activity VARCHAR(32) NOT NULL
);

CREATE TABLE food (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255)     NOT NULL,
    protein       INTEGER NOT NULL,
    fat           INTEGER NOT NULL,
    carbohydrates INTEGER NOT NULL,
    ccal          INTEGER          NOT NULL
);

CREATE TABLE meal(
    id BIGSERIAL PRIMARY KEY
    ,user_id BIGINT NOT NULL
    , food_id BIGINT NOT NULL
    , created_at TIMESTAMP NOT NULL DEFAULT now()
    , grams INTEGER NOT NULL
    , FOREIGN KEY (user_id) REFERENCES users(id)
    , FOREIGN KEY (food_id) REFERENCES food(id)
)