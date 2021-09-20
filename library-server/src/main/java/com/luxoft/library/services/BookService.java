package com.luxoft.library.services;

import com.luxoft.library.entities.Book;
import com.luxoft.library.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public long delete(long id) {
        return bookRepository.deleteById(id);
    }

    public Optional<Book> create() {
        return Optional.of(new Book());
    }

}
