CREATE TABLE users (
  username VARCHAR(255) PRIMARY KEY NOT NULL,
  password VARCHAR(255)             NOT NULL
);

CREATE TABLE quotes (
  id        BIGINT IDENTITY PRIMARY KEY NOT NULL,
  text      LONGVARCHAR                 NOT NULL,
  username  VARCHAR(255) FOREIGN KEY REFERENCES users (username),
  post_time TIMESTAMP                   NOT NULL
);

CREATE TABLE quote_scores (
  id        BIGINT IDENTITY PRIMARY KEY NOT NULL,
  quote_id  BIGINT FOREIGN KEY REFERENCES quotes (id),
  rate_time TIMESTAMP                   NOT NULL,
  score     INT                         NOT NULL,
  voter     VARCHAR(255)
);