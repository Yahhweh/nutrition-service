CREATE TABLE targets (
    id            BIGSERIAL PRIMARY KEY,
    protein       DOUBLE PRECISION NOT NULL,
    fat           DOUBLE PRECISION NOT NULL,
    carbohydrates DOUBLE PRECISION NOT NULL,
    ccal          INTEGER          NOT NULL,
    type          VARCHAR(32)      NOT NULL
);

CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    telegram_id BIGINT       NOT NULL UNIQUE,
    username    VARCHAR(255),
    weight      DOUBLE PRECISION,
    target_id   BIGINT REFERENCES targets (id)
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
    ,user_id BIGSERIAL NOT NULL
    , food_id BIGSERIAL NOT NULL
    , created_at TIMESTAMP NOT NULL DEFAULT now()
    , FOREIGN KEY (user_id) REFERENCES users(id)
    , FOREIGN KEY (food_id) REFERENCES food(id)
)