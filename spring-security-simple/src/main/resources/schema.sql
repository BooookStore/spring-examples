CREATE TABLE IF NOT EXISTS users
(
    id       INT          NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   INT          NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES users (id),
    UNIQUE (name)
);

CREATE SEQUENCE IF NOT EXISTS users_id;