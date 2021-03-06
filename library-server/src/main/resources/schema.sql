DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS genres;

CREATE TABLE genres
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    authors_id INT          NOT NULL,
    genres_id  INT          NOT NULL,
    FOREIGN KEY (authors_id) REFERENCES authors (id),
    FOREIGN KEY (genres_id) REFERENCES genres (id),
    name       VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS comments;

CREATE TABLE comments
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    books_id INT           NOT NULL,
    FOREIGN KEY (books_id) REFERENCES books (id),
    text     VARCHAR(1024) NOT NULL
);