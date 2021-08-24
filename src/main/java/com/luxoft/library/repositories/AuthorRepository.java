package com.luxoft.library.repositories;

import com.luxoft.library.entities.Author;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository extends AbstractJpaDAO<Author, Long> {

    public AuthorRepository() {
        setClazz(Author.class);
    }
}
