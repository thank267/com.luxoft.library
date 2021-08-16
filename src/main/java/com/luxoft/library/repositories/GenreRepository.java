package com.luxoft.library.repositories;

import com.luxoft.library.entities.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    
}
