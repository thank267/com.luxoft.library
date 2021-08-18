package com.luxoft.library.controllers;

import java.beans.PropertyEditorSupport;

import com.luxoft.library.repositories.BookRepository;

public class BookPropertyEditor extends PropertyEditorSupport {

    private BookRepository bookRepository;

    public BookPropertyEditor(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public void setAsText(String id) {

        setValue(bookRepository.getById(Long.valueOf(id)));
        
    }
}