DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    name     varchar(100),
    birthday DATE
);

INSERT INTO users (name, birthday)
VALUES ('Name1', '1990-01-10'),
       ('Name2', '1995-02-10'),
       ('Name3', '2000-03-10');