package com.luxoft.library.services;

import com.luxoft.library.entities.Author;
import com.luxoft.library.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    public Long save(Author author) {
        return authorRepository.save(author);
    }

    public long delete(long id) {
        return authorRepository.deleteById(id);

    }

    public Optional<Author> create() {
        return Optional.of(new Author());
    }

}
