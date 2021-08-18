package com.luxoft.library.controllers;

import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.repositories.AuthorRepository;
import com.luxoft.library.repositories.BookRepository;
import com.luxoft.library.repositories.GenreRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        AuthorPropertyEditor authorPropertyEditor = new AuthorPropertyEditor(authorRepository);
        GenrePropertyEditor genrePropertyEditor = new GenrePropertyEditor(genreRepository);
        dataBinder.registerCustomEditor(Author.class, authorPropertyEditor);
        dataBinder.registerCustomEditor(Genre.class, genrePropertyEditor);
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", bookRepository.findById(id));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "books/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute("book") @Validated Book book, @ModelAttribute("author") @Validated String author, @PathVariable("id") long id) {
        
        book.setId(id);
        bookRepository.update(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
    
}
