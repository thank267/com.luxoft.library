package com.luxoft.library.repositories;

import com.luxoft.library.entities.Genre;

import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository extends AbstractJpaDAO<Genre, Long> {

    public GenreRepository() {
        setClazz(Genre.class);
    }
}
