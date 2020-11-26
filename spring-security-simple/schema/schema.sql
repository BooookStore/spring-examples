CREATE TABLE spring.user
(
    id       INT AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE spring.role
(
    id   INT          NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES spring.user (id),
    UNIQUE (name)
);
