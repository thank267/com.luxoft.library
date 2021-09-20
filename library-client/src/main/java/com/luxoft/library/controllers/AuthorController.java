package com.luxoft.library.controllers;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.AuthorProto;
import com.luxoft.library.AuthorServiceGrpc;
import com.luxoft.library.DTO.AuthorDTO;
import com.luxoft.library.utils.ObjectMapperUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceGrpc.AuthorServiceBlockingStub authorService;

    public AuthorController(AuthorServiceGrpc.AuthorServiceBlockingStub authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", ObjectMapperUtils.mapAll(authorService.findAll(Empty.getDefaultInstance()), AuthorDTO.class));
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", Optional.ofNullable(ObjectMapperUtils.map(authorService.findById(Int64Value.of(id)), AuthorDTO.class)));
        return "authors/show";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {
        model.addAttribute("author", ObjectMapperUtils.map(authorService.create(Empty.getDefaultInstance()), AuthorDTO.class));
        return "authors/edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("author", Optional.ofNullable(ObjectMapperUtils.map(authorService.findById(Int64Value.of(id)), AuthorDTO.class)));
        return "authors/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("author") @Validated AuthorDTO author) {
        authorService.save(ObjectMapperUtils.map(author, AuthorProto.newBuilder()).build());
        return "redirect:/authors";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        authorService.delete(Int64Value.of(id));
        return "redirect:/authors";
    }

}
