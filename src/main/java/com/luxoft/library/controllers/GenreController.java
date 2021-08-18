package com.luxoft.library.controllers;

import com.luxoft.library.repositories.GenreRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/genres")
public class GenreController {

    private GenreRepository genreRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id));
        return "genres/show";
    }
    
}
