package com.luxoft.library.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Genre;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Book book;
    private Author author;
    private Genre genre;

    @BeforeEach
    public void setUp() {
        author = authorRepository.getById(1L);
        genre = genreRepository.getById(1L);
        book = new Book();

        book.setName("Test name");
        book.setAuthor(author);
        book.setGenre(genre);

    }

    @Test
    @Transactional
    public void saveAndFindById() {

        Long id = bookRepository.save(book);
        Book fetchedBook = bookRepository.findById(book.getId()).get();
        assertThat(id == fetchedBook.getId()).isTrue();
        assertThat("Test name" == fetchedBook.getName()).isTrue();
        assertThat(1 == fetchedBook.getAuthor().getId()).isTrue();
        assertThat(1 == fetchedBook.getGenre().getId()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndGetById() {

        Long id = bookRepository.save(book);
        Book fetchedBook = bookRepository.getById(book.getId());
        assertThat(id == fetchedBook.getId()).isTrue();
        assertThat("Test name" == fetchedBook.getName()).isTrue();
        assertThat(1 == fetchedBook.getAuthor().getId()).isTrue();
        assertThat(1 == fetchedBook.getGenre().getId()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndDeleteByID() {
        Long id = bookRepository.save(book);
        bookRepository.deleteById(id);
        Optional optional = bookRepository.findById(id);
        assertThat(Optional.empty() == optional).isTrue();
        //assertThat(author.getBooks().contains(book)).isFalse();
    }

    @Test
    @Transactional
    public void saveAndDelete() {
        Long id = bookRepository.save(book);
        book = bookRepository.getById(id);
        bookRepository.delete(book);
        Optional optional = bookRepository.findById(id);
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void testSaveAllFindAll() {
        Book book1 = new Book();
        book1.setId(10L);
        book1.setName("Test name1");
        book1.setAuthor(author);
        book1.setGenre(genre);
        Book book2 = new Book();
        book2.setName("Test name2");
        book2.setAuthor(author);
        book2.setGenre(genre);

        Long id1 = bookRepository.save(book1);
        Long id2 = bookRepository.save(book2);
        List<Book> bookList = (List<Book>) bookRepository.findAll();

        assertThat(bookList.isEmpty()).isFalse();
        assertThat(bookList.contains(book2)).isTrue();

    }

    @Test
    @Transactional
    public void testSaveWithoutAuthorThanException() {
        Book book1 = new Book();
        book1.setName("Test name1");
        book1.setGenre(genre);

        assertThrows(DataIntegrityViolationException.class, () -> bookRepository.save(book1));

    }

    @Test
    @Transactional
    public void testSaveWithoutGenreThanException() {
        Book book1 = new Book();
        book1.setName("Test name1");
        book1.setAuthor(author);
        assertThrows(DataIntegrityViolationException.class, () -> bookRepository.save(book1));

    }

    @Test
    @Transactional
    public void testCascadeDeleteAuthor() {
        Book book = new Book();

        book.setName("Test name1");
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);

        List<Book> bookList = (List<Book>) bookRepository.findAll();
        assertThat(bookList.contains(book)).isTrue();

        authorRepository.delete(author);

        bookList = (List<Book>) bookRepository.findAll();
        assertThat(bookList.contains(book)).isFalse();

    }

    @AfterEach
    public void tearDown() {

        bookRepository.deleteAll();
        book = null;
        genre = null;
        author = null;
    }

}
