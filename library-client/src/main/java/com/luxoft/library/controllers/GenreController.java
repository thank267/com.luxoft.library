package com.luxoft.library.controllers;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.luxoft.library.DTO.GenreDTO;
import com.luxoft.library.GenreServiceGrpc;
import com.luxoft.library.utils.ObjectMapperUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreServiceGrpc.GenreServiceBlockingStub genreService;

    public GenreController(GenreServiceGrpc.GenreServiceBlockingStub genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", ObjectMapperUtils.mapAll(genreService.findAll(Empty.getDefaultInstance()), GenreDTO.class));
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", Optional.ofNullable(ObjectMapperUtils.map(genreService.findById(Int64Value.of(id)), GenreDTO.class)));
        return "genres/show";
    }

}
