package com.luxoft.library.repositories;

import com.luxoft.library.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {


    
}
