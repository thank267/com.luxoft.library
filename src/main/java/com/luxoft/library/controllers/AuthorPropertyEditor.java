package com.luxoft.library.controllers;

import java.beans.PropertyEditorSupport;

import com.luxoft.library.repositories.AuthorRepository;

public class AuthorPropertyEditor extends PropertyEditorSupport {

    private AuthorRepository authorRepository;

    public AuthorPropertyEditor(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    public void setAsText(String id) {

        setValue(authorRepository.getById(Long.valueOf(id)));
        
    }
}