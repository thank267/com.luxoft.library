package com.luxoft.library.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.standard.expression.BooleanTokenExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;

import com.luxoft.library.entities.Genre;
import com.luxoft.library.DTO.GenreDTO;
import com.luxoft.library.entities.Author;
import com.luxoft.library.DTO.AuthorDTO;
import com.luxoft.library.entities.Book;
import com.luxoft.library.DTO.BookDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ObjectMapperUtilsTest {


    @Test
    public void whenConvertGenreEntityToGenretDto_thenCorrect() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test genre");

        GenreDTO genreDTO = ObjectMapperUtils.map(genre, GenreDTO.class);
        assertThat(genre.getId() == genreDTO.getId()).isTrue();
        assertThat(genre.getName() == genreDTO.getName()).isTrue();

    }

    @Test
    public void whenConvertGenreDtoToGenreEntity_thenCorrect() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Test genre");

        Genre genre = ObjectMapperUtils.map(genreDTO, new Genre());
        assertThat(genre.getId() == genreDTO.getId()).isTrue();
        assertThat(genre.getName() == genreDTO.getName()).isTrue();
    }

    @Test
    public void whenConvertListGenreEntityToListGenretDto_thenCorrect() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Test genre 1");

        Genre genre2 = new Genre();
        genre2.setId(1L);
        genre2.setName("Test genre 1");

        List<Genre> genreList = new ArrayList<Genre>();
        genreList.add(genre1);
        genreList.add(genre2);

        List<GenreDTO> genreListDTO = ObjectMapperUtils.mapAll(genreList, GenreDTO.class);
        assertThat(genreList.get(0).getName() == genreListDTO.get(0).getName()).isTrue();
        assertThat(genreList.size() == genreListDTO.size()).isTrue();
    }

    @Test
    public void whenConvertAuthorEntityToAuthorDto_thenCorrect() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Test author");

        AuthorDTO authorDTO = ObjectMapperUtils.map(author, AuthorDTO.class);
        assertThat(author.getId() == authorDTO.getId()).isTrue();
        assertThat(author.getName() == authorDTO.getName()).isTrue();

    }

    @Test
    public void whenConvertAuthorDtoToAuthorEntity_thenCorrect() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Test author");

        Author author = ObjectMapperUtils.map(authorDTO, new Author());
        assertThat(author.getId() == authorDTO.getId()).isTrue();
        assertThat(author.getName() == authorDTO.getName()).isTrue();
    }

    @Test
    public void whenConvertListAuthrEntityToListAuthorDto_thenCorrect() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("Test author 1");

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Test author 2");

        List<Author> authorList = new ArrayList<Author>();
        authorList.add(author1);
        authorList.add(author2);

        List<AuthorDTO> authorListDTO = ObjectMapperUtils.mapAll(authorList, AuthorDTO.class);
        assertThat(authorList.get(0).getName() == authorListDTO.get(0).getName()).isTrue();
        assertThat(authorList.size() == authorListDTO.size()).isTrue();
    }

    @Test
    public void whenConvertBookEntityToBookDto_thenCorrect() {

        Author author = new Author();
        author.setId(1L);
        author.setName("Test author 1");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test genre");

        Book book = new Book();
        book.setId(1L);
        book.setName("Test book");
        book.setAuthor(author);
        book.setGenre(genre);

        BookDTO bookDTO = ObjectMapperUtils.map(book, BookDTO.class);

        assertThat(book.getId() == bookDTO.getId()).isTrue();
        assertThat(book.getName() == bookDTO.getName()).isTrue();
        assertThat(author.getId() == Long.valueOf(bookDTO.getAuthorId())).isTrue();
        assertThat(author.getName() == bookDTO.getAuthorName()).isTrue();
        assertThat(genre.getId() == Long.valueOf(bookDTO.getGenreId())).isTrue();
        assertThat(genre.getName() == bookDTO.getGenreName()).isTrue();

    }

    @Test
    public void whenConvertBookDtoToBookEntity_thenCorrect() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setName("Test book");
        bookDTO.setAuthorId("1");
        bookDTO.setAuthorName("Test author");
        bookDTO.setGenreId("1");
        bookDTO.setGenreName("Test genre");

        Author author = new Author();
        author.setId(1L);
        author.setName("Test author");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Test genre");

        Book book = ObjectMapperUtils.map(bookDTO, new Book());
        assertThat(book.getId() == bookDTO.getId()).isTrue();
        assertThat(book.getName() == bookDTO.getName()).isTrue();
        assertThat(book.getAuthor().getId() == Long.valueOf(bookDTO.getAuthorId())).isTrue();
        assertThat(book.getAuthor().getName() == bookDTO.getAuthorName()).isTrue();
        assertThat(book.getGenre().getId() == Long.valueOf(bookDTO.getGenreId())).isTrue();
        assertThat(book.getGenre().getName() == bookDTO.getGenreName()).isTrue();
    }

}
