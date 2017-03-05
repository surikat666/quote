CREATE TABLE users
(
  username VARCHAR(255) PRIMARY KEY NOT NULL,
  password VARCHAR(255)             NOT NULL
);

CREATE TABLE quotes
(
  id        BIGSERIAL PRIMARY KEY   NOT NULL,
  text      TEXT                    NOT NULL,
  username  VARCHAR(255) REFERENCES users (username),
  post_time TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE quote_scores
(
  id        BIGSERIAL PRIMARY KEY   NOT NULL,
  quote_id  BIGINT REFERENCES quotes (id),
  score     INTEGER DEFAULT 0       NOT NULL,
  rate_time TIMESTAMP DEFAULT now() NOT NULL,
  voter     VARCHAR(255) REFERENCES users (username)
);

