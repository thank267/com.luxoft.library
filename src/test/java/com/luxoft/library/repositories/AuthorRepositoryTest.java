package com.luxoft.library.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.luxoft.library.entities.Author;

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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Test name");

    }

    @Test
    @Transactional
    public void saveAndFindById() {

        authorRepository.save(author);
        Author fetchedAuthor = authorRepository.findById(author.getId()).get();
        assertThat(1 == fetchedAuthor.getId()).isTrue();
        assertThat("Test name" == fetchedAuthor.getName()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndGetById() {

        authorRepository.save(author);
        Author fetchedAuthor = authorRepository.getById(author.getId());
        assertThat(1 == fetchedAuthor.getId()).isTrue();
        assertThat("Test name" == fetchedAuthor.getName()).isTrue();

    }

    @Test
    @Transactional
    public void saveAndDeleteByID() {
        authorRepository.save(author);
        authorRepository.deleteById(author.getId());
        Optional optional = authorRepository.findById(author.getId());
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void saveAndDelete() {
        authorRepository.save(author);
        author = authorRepository.getById(author.getId());
        authorRepository.delete(author);
        Optional optional = authorRepository.findById(author.getId());
        assertThat(Optional.empty() == optional).isTrue();
    }

    @Test
    @Transactional
    public void testSaveAllFindAll() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("Test name1");
        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Test name2");

        authorRepository.save(author1);
        authorRepository.save(author2);
        List<Author> authorList = (List<Author>) authorRepository.findAll();
        assertThat("Test name3" == authorList.get(0).getName()).isFalse();
        assertThat("Test name2" == authorList.get(1).getName()).isTrue();
    }

    @AfterEach
    public void tearDown() {

        authorRepository.deleteAll();
        author = null;
    }

}
