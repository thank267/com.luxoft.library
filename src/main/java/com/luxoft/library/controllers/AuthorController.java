package com.luxoft.library.controllers;

import com.luxoft.library.entities.Author;
import com.luxoft.library.repositories.AuthorRepository;

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

    private AuthorRepository authorRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorRepository.findById(id));
        return "authors/show";
    }

    @GetMapping("/new")
    public String newAuthor(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("author") @Validated Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("author", authorRepository.findById(id));
        return "authors/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute("author") @Validated Author author, @PathVariable("id") long id) {
        author.setId(id);
        authorRepository.update(author);
        return "redirect:/authors";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
    
}
