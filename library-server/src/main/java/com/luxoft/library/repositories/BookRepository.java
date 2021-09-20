package com.luxoft.library.repositories;

import com.luxoft.library.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends AbstractJpaDAO<Book, Long> {

    public BookRepository() {
        setClazz(Book.class);
    }
}
