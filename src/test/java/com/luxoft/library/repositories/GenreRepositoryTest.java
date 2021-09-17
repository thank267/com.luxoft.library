package com.luxoft.library.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Genre;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    private Genre genre;

    @BeforeEach
    public void setUp() {
        genre = new Genre();
        genre.setId(1L);
        genre.setName("Test name");

    }

    @Test
    @Transactional
    public void saveAndFindById() {

        genreRepository.save(genre);
        Genre fetchedGenre = genreRepository.findById(genre.getId()).get();
        assertThat(1 == fetchedGenre.getId()).isTrue();
        assertThat("Test name" == fetchedGenre.getName()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndGetById() {

        genreRepository.save(genre);
        Genre fetchedGenre = genreRepository.getById(genre.getId());
        assertThat(1 == fetchedGenre.getId()).isTrue();
        assertThat("Test name" == fetchedGenre.getName()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndDeleteByID() {
        genreRepository.save(genre);
        genreRepository.deleteById(genre.getId());
        Optional optional = genreRepository.findById(genre.getId());
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void saveAndDelete() {
        genreRepository.save(genre);
        genre = genreRepository.getById(genre.getId());
        genreRepository.delete(genre);
        Optional optional = genreRepository.findById(genre.getId());
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void testSaveAllFindAll() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Test name1");
        Genre genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Test name2");

        genreRepository.save(genre1);
        genreRepository.save(genre2);
        List<Genre> genreList = (List<Genre>) genreRepository.findAll();
        assertThat("Test name3" == genreList.get(0).getName()).isFalse();
        assertThat("Test name2" == genreList.get(1).getName()).isTrue();
    }

    @AfterEach
    public void tearDown() {

        genreRepository.deleteAll();
        genre = null;
    }

}
