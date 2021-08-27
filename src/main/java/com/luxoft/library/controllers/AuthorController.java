package com.luxoft.library.controllers;

import com.luxoft.library.DTO.AuthorDTO;
import com.luxoft.library.entities.Author;
import com.luxoft.library.services.AuthorService;
import com.luxoft.library.utils.ObjectMapperUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/authors")
public class AuthorController {

    

    private final AuthorService authorService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", ObjectMapperUtils.mapAll(authorService.findAll(), AuthorDTO.class));
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorService.findById(id).map(au -> ObjectMapperUtils.map(au, AuthorDTO.class)));
        return "authors/show";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {
        model.addAttribute("author", authorService.create().map(au -> ObjectMapperUtils.map(au, AuthorDTO.class)));
        return "authors/edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("author", authorService.findById(id).map(au -> ObjectMapperUtils.map(au, AuthorDTO.class)));
        return "authors/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("author") @Validated AuthorDTO author) {
        authorService.save(ObjectMapperUtils.map(author, new Author()));
        return "redirect:/authors";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
