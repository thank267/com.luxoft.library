package com.luxoft.library.services;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Book;
import com.luxoft.library.repositories.BookRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    public long save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> create() {
        return Optional.of(new Book());
    }

}
