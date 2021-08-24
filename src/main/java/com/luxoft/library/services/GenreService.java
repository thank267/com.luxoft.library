package com.luxoft.library.services;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Genre;
import com.luxoft.library.repositories.GenreRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }

    public Long save(Genre genre) {
        return genreRepository.save(genre);
    }

}
