package com.luxoft.library.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Comment;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.repositories.AuthorRepository;
import com.luxoft.library.repositories.BookRepository;
import com.luxoft.library.repositories.GenreRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    

    private Comment comment;
    private Book book;
    private Author author;
    private Genre genre;

    @BeforeEach
    public void setUp() {

        author = authorRepository.getById(1L);

        book = bookRepository.getById(1L);
       
        comment = new Comment();
        comment.setText("Test text");
        comment.setBook(book);

    }

    @Test
    @Transactional
    public void saveAndFindById() {
        
        Long id = commentRepository.save(comment);
        Comment fetchedComment = commentRepository.findById(comment.getId()).get();
        assertThat(id == fetchedComment.getId()).isTrue();
        assertThat("Test text" == fetchedComment.getText()).isTrue();
        assertThat(1 == fetchedComment.getBook().getId()).isTrue();
        assertThat(book.getComments().contains(comment)).isTrue();

    }

    @Test
    @Transactional
    public void saveAndGetById() {

        Long id = commentRepository.save(comment);
        Comment fetchedComment = commentRepository.getById(comment.getId());
        assertThat(id == fetchedComment.getId()).isTrue();
        assertThat("Test text" == fetchedComment.getText()).isTrue();
        assertThat(1 == fetchedComment.getBook().getId()).isTrue();
        assertThat(book.getComments().contains(comment)).isTrue();

    }

    @Test
    @Transactional
    public void saveAndDeleteByID() {
        Long id = commentRepository.save(comment);
        commentRepository.deleteById(id);
        Optional optional = commentRepository.findById(id);
        assertThat(Optional.empty() == optional).isTrue();
        id = bookRepository.save(book);
        book = bookRepository.getById(id);
        //assertThat(book.getComments().contains(comment)).isFalse();
    }

    @Test
    @Transactional
    public void saveAndDelete() {
        Long id = commentRepository.save(comment);
        comment = commentRepository.getById(id);
        commentRepository.delete(comment);
        Optional optional = commentRepository.findById(id);
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void testSaveAllFindAll() {
        Comment comment1 = new Comment();
        comment1.setText("Test text1");
        comment1.setBook(book);

        Comment comment2 = new Comment();
        comment2.setText("Test text2");
        comment2.setBook(book);

        Long id1 = commentRepository.save(comment1);
        Long id2 = commentRepository.save(comment2);
        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        assertThat(commentList.isEmpty()).isFalse();
        assertThat(commentList.contains(comment2)).isTrue();

    }

    @Test
    @Transactional
    public void testSaveWithoutBookThanException() {
        Comment comment1 = new Comment();
        comment1.setText("Test text1");

        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.save(comment1));

    }

    @Test
    @Transactional
    public void testCascadeDeleteBook() {
        Comment comment1 = new Comment();
        comment1.setText("Test text1");
        comment1.setBook(book);

        commentRepository.save(comment1);

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();
        assertThat(commentList.contains(comment1)).isTrue();

        bookRepository.delete(book);

        commentList = (List<Comment>) commentRepository.findAll();
        assertThat(commentList.contains(comment1)).isFalse();

    }

    @Test
    @Transactional
    public void testCascadeDeleteAuthor() {
        Comment comment1 = new Comment();
        comment1.setText("Test text1");
        comment1.setBook(book);

        commentRepository.save(comment1);

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();
        assertThat(commentList.contains(comment1)).isTrue();

        authorRepository.delete(author);

        commentList = (List<Comment>) commentRepository.findAll();
        assertThat(commentList.contains(comment1)).isFalse();

    }

    @AfterEach
    @Transactional
    public void tearDown() {

        commentRepository.deleteAll();
        book = null;
        genre = null;
        author = null;
    }

}
