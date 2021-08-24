package com.luxoft.library.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.library.entities.Genre;
import com.luxoft.library.repositories.GenreRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Autowired
    @InjectMocks
    private GenreService genreService;
    private Genre genre1;
    private Genre genre2;
    List<Genre> genreList;

    @BeforeEach
    public void setUp() {
        genreList = new ArrayList<>();
        genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Test name 1");
        genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Test name 2");
        genreList.add(genre1);
        genreList.add(genre2);
    }

    @Test
    void givenProductToAddShouldReturnAddedProduct() {
        // stubbing
        when(genreRepository.save(any())).thenReturn(genre1.getId());
        genreService.save(genre1);
        verify(genreRepository, times(1)).save(any());
    }

    @AfterEach
    public void tearDown() {
        genre1 = genre1 = null;
        genreList = null;
    }

}
