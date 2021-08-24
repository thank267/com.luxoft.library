package com.luxoft.library.controllers;

import com.luxoft.library.services.GenreService;
import com.luxoft.library.DTO.GenreDTO;
import com.luxoft.library.utils.ObjectMapperUtils;

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

    private final GenreService genreService;

    private final ObjectMapperUtils objectMapperUtils;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", objectMapperUtils.mapAll(genreService.findAll(), GenreDTO.class));
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", genreService.findById(id).map(ge -> objectMapperUtils.map(ge, GenreDTO.class)));
        return "genres/show";
    }

}
