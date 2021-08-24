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

import com.luxoft.library.entities.Comment;
import com.luxoft.library.entities.Book;
import com.luxoft.library.repositories.CommentRepository;
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
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private CommentService commentService;
    private Comment comment1;
    private Comment comment2;
    private Book book;

    List<Comment> commentList;

    @BeforeEach
    public void setUp() {

        commentList = new ArrayList<>();
        comment1 = new Comment();
        comment1.setText("Test text 1");
        comment1.setId(10L);

        comment2 = new Comment();
        comment2.setText("Test text 2");
        comment2.setId(20L);

        commentList.add(comment1);
        commentList.add(comment2);

        book = new Book();
        book.setName("Test text 2");
        book.setId(1L);

    }

    @Test
    void saveAnyGetAny() {
        when(commentRepository.save(any())).thenReturn(comment1.getId());
        commentService.save(comment1);
        verify(commentRepository, times(1)).save(any());
    }

    @Test
    void saveAllGetAll() {
        when(commentRepository.save(comment1)).thenReturn(comment1.getId());
        commentService.save(comment1);
        when(commentRepository.findAll()).thenReturn(commentList);
        List<Comment> commenList1 = commentService.findAll();
        assertEquals(commenList1, commentList);
        verify(commentRepository, times(1)).save(comment1);
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void givenIdGetAuthorById() {
        when(commentRepository.findById(comment1.getId())).thenReturn(Optional.ofNullable(comment1));
        commentService.findById(comment1.getId());
        verify(commentRepository, times(1)).findById(comment1.getId());
    }

    @Test
    void givenIdDeleteAuthorById() {
        when(commentRepository.deleteById(comment1.getId())).thenReturn(comment1.getId());
        commentService.delete(comment1.getId());
        verify(commentRepository, times(1)).deleteById(comment1.getId());
    }

    @Test
    void create() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        commentService.create(1L);
        verify(bookRepository, times(1)).findById(book.getId());
        assertThat(commentService.create(1L)).isEqualTo(Optional.of(new Comment()));
       
    }

    @AfterEach
    public void tearDown() {
        comment1 = comment2 = null;
        commentList = null;
    }

}
