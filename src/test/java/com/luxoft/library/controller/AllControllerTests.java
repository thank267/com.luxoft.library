package com.luxoft.library.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
public class AllControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("genreController"));
        Assertions.assertNotNull(webApplicationContext.getBean("authorController"));
        Assertions.assertNotNull(webApplicationContext.getBean("bookController"));
        Assertions.assertNotNull(webApplicationContext.getBean("commentController"));

        Assertions.assertNotNull(webApplicationContext.getBean("genreRepository"));
        Assertions.assertNotNull(webApplicationContext.getBean("authorRepository"));
        Assertions.assertNotNull(webApplicationContext.getBean("bookRepository"));
        Assertions.assertNotNull(webApplicationContext.getBean("commentRepository"));
    }
    
    
    @Test
    public void allGenresTest() throws Exception {

        mockMvc.perform(get("/genres")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(stringContainsInOrder("Фантастика", "Ужасы", "Проза", "Приключения")));

    }
    
    @Test
    public void _4GenreTest() throws Exception {

        mockMvc.perform(get("/genres/4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Приключения")));

        mockMvc.perform(get("/genres/4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Фантастика"))));

        mockMvc.perform(get("/genres/4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Ужасы"))));

        mockMvc.perform(get("/genres/4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Проза"))));

    }
    
    @Test
    public void allAuthorsTest() throws Exception {
        mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Артур")));

    }

    @Test
    public void _1AuthorTest() throws Exception {

        mockMvc.perform(get("/authors/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Артур")));

        mockMvc.perform(get("/authors/10")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Автор не найден")));

    }
    
    @Test
    public void newAuthorTest() throws Exception {

        String paramName = "name";

        String name = "Автор new";

        mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString(name))));
        

        mockMvc.perform(get("/authors/new")).andDo(print()).andExpect(status().isOk())
                .andExpect(xpath("//input[@name='" + paramName + "']").exists());

        MockHttpServletRequestBuilder createMessage = post("/authors/")
               
                .param(paramName, name);

        mockMvc.perform(createMessage).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/authors"));

        mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(name))); 
    }

    @Test
    public void allBooksTest() throws Exception {
            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Дюна")));

    }

    @Test
    public void _1BookTest() throws Exception {

            mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Дюна")));

            mockMvc.perform(get("/books/10")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Книга не найдена")));

    }

    @Test
    public void newBookTest() throws Exception {

            String paramName = "name";

            String name = "Книга new";

            String paramAuthor = "author";

            String author = "1";

            String paramGenre = "genre";

            String genre = "1";

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(name))));

            mockMvc.perform(get("/books/new")).andDo(print()).andExpect(status().isOk())
                            .andExpect(xpath("//input[@name='" + paramName + "']").exists())
                            .andExpect(xpath("//select[@name='" + paramAuthor + "']").exists())
                            .andExpect(xpath("//select[@name='" + paramGenre + "']").exists());

            MockHttpServletRequestBuilder createMessage = post("/books/").param(paramName, name)
                            .param(paramAuthor, author).param(paramGenre, genre);
            mockMvc.perform(createMessage).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/books"));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(name)));
    }
    
    @Test
    public void _1CommentTest() throws Exception {

            mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Прекрасная фантастика")));

    }
    
    @Test
    public void newCommentTest() throws Exception {

            String paramText = "text";

            String text = "Комментарий new";

            String paramBook = "book";

            String book = "1";

            mockMvc.perform(get("/books/" + book)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(text))));

            mockMvc.perform(get("/comments/new?bookId=" + book)).andDo(print()).andExpect(status().isOk())
                            .andExpect(xpath("//input[@name='" + paramText + "']").exists())
                            .andExpect(xpath("//input[@name='" + paramBook + "']").exists());

            MockHttpServletRequestBuilder createMessage = post("/comments/").param(paramText, text).param(paramBook,
                            book);
            mockMvc.perform(createMessage).andExpect(status().is3xxRedirection())
                            .andExpect(redirectedUrl("/books/" + book));

            mockMvc.perform(get("/books/" + book)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(text)));
    }
    
    @Test
    public void editAuthorAndAllChildrenExistsTest() throws Exception {

            String paramName = "name";

            String oldAuthorName = "Линда";

            String newAuthorName = "Автор edit";

            String authorId = "2";

            String bookId = "2";

            String commentText = "Захватывающее чтение";

            mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(newAuthorName))))
                            .andExpect(content().string(containsString(oldAuthorName)));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(newAuthorName))))
                            .andExpect(content().string(containsString(oldAuthorName)));

            mockMvc.perform(get("/books/" + bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(newAuthorName))))
                            .andExpect(content().string(containsString(oldAuthorName)))
                            .andExpect(content().string(containsString(commentText)));

            mockMvc.perform(get("/authors/" + authorId + "/edit")).andDo(print()).andExpect(status().isOk())
                            .andExpect(xpath("//input[@name='" + paramName + "']").exists());

            MockHttpServletRequestBuilder createMessage = post("/authors/" + authorId + "/update")

                            .param(paramName, newAuthorName);

            mockMvc.perform(createMessage).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/authors"));

            mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(newAuthorName)))
                            .andExpect(content().string(not(containsString(oldAuthorName))));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(newAuthorName)))
                            .andExpect(content().string(not(containsString(oldAuthorName))));

            mockMvc.perform(get("/books/" + bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(newAuthorName)))
                            .andExpect(content().string(not(containsString(oldAuthorName))))
                            .andExpect(content().string(containsString(commentText)));
    }

    @Test
    public void editBookAndAllChildrenExistsTest() throws Exception {

            String paramAuthor = "author";

            String paramGenre = "genre";

            String paramName = "name";

            String oldBookName = "Зори здесь";

            String newBookName = "Книга edit";

            String bookId = "2";

            String authorId = "2";

            String genreId = "3";

            String commentText = "Захватывающее чтение";

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(newBookName))))
                            .andExpect(content().string(containsString(oldBookName)));

            mockMvc.perform(get("/books/" + bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(newBookName))))
                            .andExpect(content().string(containsString(oldBookName)))
                            .andExpect(content().string(containsString(commentText)));

            mockMvc.perform(get("/books/" + bookId + "/edit")).andDo(print()).andExpect(status().isOk())
                            .andExpect(xpath("//input[@name='" + paramName + "']").exists())
                            .andExpect(xpath("//select[@name='" + paramAuthor + "']").exists())
                            .andExpect(xpath("//select[@name='" + paramGenre + "']").exists());

            MockHttpServletRequestBuilder createMessage = post("/books/" + bookId + "/update")
                            .param(paramName, newBookName).param(paramAuthor, authorId).param(paramGenre, genreId);

            mockMvc.perform(createMessage).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/books"));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(newBookName)))
                            .andExpect(content().string(not(containsString(oldBookName))));

            mockMvc.perform(get("/books/" + bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(newBookName)))
                            .andExpect(content().string(not(containsString(oldBookName))))
                            .andExpect(content().string(containsString(commentText)));
    }

    @Test
    public void deleteAuthorAndAllChildrenExistsTest() throws Exception {

            String paramName = "name";

            String authorName = "Конан";

            String authorId = "3";

            String bookName = "Собака";

            String bookId = "3";

            String commentText = "Страшная книга";

            

            mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(authorName)));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(bookName)));

            mockMvc.perform(get("/books/" + bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString(
                                            authorName)))
                            .andExpect(content().string(containsString(
                                            bookName)))
                            .andExpect(content().string(containsString(commentText)));

            mockMvc.perform(get("/authors/" + authorId + "/delete")).andDo(print()).andExpect(status().is3xxRedirection())
                            .andExpect(redirectedUrl("/authors"));

            mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(authorName))));

            mockMvc.perform(get("/authors/"+ authorId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Автор не найден")));

            mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(not(containsString(authorName))))
                            .andExpect(content().string(not(containsString(bookName))));

            mockMvc.perform(get("/books/"+ bookId)).andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(containsString("Книга не найдена")));
    }
    
    
    
    

    

}