INSERT INTO genres (name) VALUES 
    ('Фантастика'),
    ('Ужасы'),
    ('Проза'),
    ('Приключения');

INSERT INTO authors (name) VALUES 
    ('Артур'),
    ('Линда'),
    ('Конан');

INSERT INTO books (name, authors_id, genres_id) VALUES 
    ('Дюна',1,1),
    ('Зори здесь',2,3),
    ('Собака',3,2);

INSERT INTO comments (text, books_id) VALUES 
    ('Прекрасная фантастика',1),
    ('Захватывающее чтение',2),
    ('Страшная книга',3);




