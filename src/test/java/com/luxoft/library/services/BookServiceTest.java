package com.luxoft.library.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Book;
import com.luxoft.library.repositories.BookRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookService bookService;
    private Book book1;
    private Book book2;

    List<Book> bookList;

    @BeforeEach
    public void setUp() {

        bookList = new ArrayList<>();
        book1 = new Book();
        book1.setName("Test name 1");
        book1.setId(10L);

        book2 = new Book();
        book2.setName("Test name 2");
        book2.setId(20L);

        bookList.add(book1);
        bookList.add(book2);

    }

    @Test
    void saveAnyGetAny() {
        when(bookRepository.save(book1)).thenReturn(book1.getId());
        bookService.save(book1);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void saveAllGetAll() {
        when(bookRepository.save(book1)).thenReturn(book1.getId());
        bookService.save(book1);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> bookList1 = bookService.findAll();
        assertEquals(bookList1, bookList);
        verify(bookRepository, times(1)).save(book1);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void givenIdGetAuthorById() {
        when(bookRepository.findById(book1.getId())).thenReturn(Optional.ofNullable(book1));
        bookService.findById(book1.getId());
        verify(bookRepository, times(1)).findById(book1.getId());
    }

    @Test
    void givenIdDeleteAuthorById() {
        when(bookRepository.deleteById(book1.getId())).thenReturn(book1.getId());
        bookService.delete(book1.getId());
        verify(bookRepository, times(1)).deleteById(book1.getId());
    }

    @Test
    void create() {
        assertThat(bookService.create()).isEqualTo(Optional.of(new Book()));
    }

    @AfterEach
    public void tearDown() {
        book1 = book2 = null;
        bookList = null;
    }

}
