package com.luxoft.library.controllers;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.AuthorServiceGrpc;
import com.luxoft.library.BookProto;
import com.luxoft.library.BookServiceGrpc;
import com.luxoft.library.DTO.AuthorDTO;
import com.luxoft.library.DTO.BookDTO;
import com.luxoft.library.DTO.GenreDTO;
import com.luxoft.library.GenreServiceGrpc;
import com.luxoft.library.utils.ObjectMapperUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceGrpc.BookServiceBlockingStub bookService;
    private final AuthorServiceGrpc.AuthorServiceBlockingStub authorService;
    private final GenreServiceGrpc.GenreServiceBlockingStub genreService;

    public BookController(BookServiceGrpc.BookServiceBlockingStub bookService,
                          AuthorServiceGrpc.AuthorServiceBlockingStub authorService,
                          GenreServiceGrpc.GenreServiceBlockingStub genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", ObjectMapperUtils.mapAll(bookService.findAll(Empty.getDefaultInstance()), BookDTO.class));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", Optional.ofNullable(ObjectMapperUtils.map(bookService.findById(Int64Value.of(id)), BookDTO.class)));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", ObjectMapperUtils.map(bookService.create(Empty.getDefaultInstance()), BookDTO.class));
        return "books/edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", Optional.ofNullable(ObjectMapperUtils.map(bookService.findById(Int64Value.of(id)), BookDTO.class)));
        model.addAttribute("authors", ObjectMapperUtils.mapAll(authorService.findAll(Empty.getDefaultInstance()), AuthorDTO.class));
        model.addAttribute("genres", ObjectMapperUtils.mapAll(genreService.findAll(Empty.getDefaultInstance()), GenreDTO.class));
        return "books/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Validated BookDTO author) {
        bookService.save(ObjectMapperUtils.map(author, BookProto.newBuilder()).build());
        return "redirect:/books";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        bookService.delete(Int64Value.of(id));
        return "redirect:/books";
    }

}
