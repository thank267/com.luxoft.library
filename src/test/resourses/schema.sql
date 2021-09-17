CREATE TABLE IF NOT EXISTS authors
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    250
) NOT NULL
    );

CREATE TABLE genres IF NOT EXISTS
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    250
) NOT NULL
    );

CREATE TABLE books IF NOT EXISTS
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    authors_id
    INT
    NOT
    NULL,
    genres_id
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    authors_id
) REFERENCES authors
(
    id
),
    FOREIGN KEY
(
    genres_id
) REFERENCES genres
(
    id
),
    name VARCHAR
(
    250
) NOT NULL
    );

CREATE TABLE comments IF NOT EXISTS
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    books_id
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    books_id
) REFERENCES books
(
    id
),
    text VARCHAR
(
    1024
) NOT NULL
    );


