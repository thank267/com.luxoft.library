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

import com.luxoft.library.entities.Author;
import com.luxoft.library.repositories.AuthorRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Autowired
    @InjectMocks
    private AuthorService authorService;
    private Author аuthor1;
    private Author аuthor2;
    List<Author> authorList;

    @BeforeEach
    public void setUp() {
        authorList = new ArrayList<>();
        аuthor1 = new Author();
        аuthor1.setName("Test name 1");
        аuthor1.setId(1L);
        аuthor2 = new Author();
        аuthor2.setName("Test name 2");
        аuthor2.setId(2L);
        authorList.add(аuthor1);
        authorList.add(аuthor2);
    }

    @Test
    void saveAnyGetAny() {
        when(authorRepository.save(any())).thenReturn(аuthor1.getId());
        authorService.save(аuthor1);
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    void saveAllGetAll() {
        authorRepository.save(аuthor1);
        when(authorRepository.findAll()).thenReturn(authorList);
        List<Author> authorList1 = authorService.findAll();
        assertEquals(authorList1, authorList);
        verify(authorRepository, times(1)).save(аuthor1);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void givenIdGetAuthorById() {
        when(authorRepository.findById(аuthor1.getId())).thenReturn(Optional.ofNullable(аuthor1));
        authorService.findById(аuthor1.getId());
        verify(authorRepository, times(1)).findById(аuthor1.getId());
    }

    @Test
    void givenIdDeleteAuthorById() {
        when(authorRepository.deleteById(аuthor1.getId())).thenReturn(аuthor1.getId());
        authorService.delete(аuthor1.getId());
        verify(authorRepository, times(1)).deleteById(аuthor1.getId());
    }

    @Test
    void create() {
        assertThat(authorService.create()).isEqualTo(Optional.of(new Author()));
    }

    @AfterEach
    public void tearDown() {
        аuthor1 = аuthor2 = null;
        authorList = null;
    }

}
