package com.luxoft.library.controllers;

import java.beans.PropertyEditorSupport;

import com.luxoft.library.repositories.GenreRepository;

public class GenrePropertyEditor extends PropertyEditorSupport {

    private GenreRepository genreRepository;

    public GenrePropertyEditor(GenreRepository renreRepository) {

        this.genreRepository = renreRepository;
    }
  

    public void setAsText(String id) {
        
        setValue(genreRepository.getById(Long.valueOf(id)));
        
    }
}