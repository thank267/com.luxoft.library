package com.luxoft.library.controllers;

import com.luxoft.library.DTO.BookDTO;
import com.luxoft.library.entities.Book;
import com.luxoft.library.services.AuthorService;
import com.luxoft.library.services.BookService;
import com.luxoft.library.services.GenreService;
import com.luxoft.library.utils.ObjectMapperUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

  

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", ObjectMapperUtils.mapAll(bookService.findAll(), BookDTO.class));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.findById(id).map(au -> ObjectMapperUtils.map(au, BookDTO.class)));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", bookService.create().map(au -> ObjectMapperUtils.map(au, BookDTO.class)));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "books/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") BookDTO book) {
        bookService.save(ObjectMapperUtils.map(book, new Book()));
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", bookService.findById(id).map(au -> ObjectMapperUtils.map(au, BookDTO.class)));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "books/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

}
