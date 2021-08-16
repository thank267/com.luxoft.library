package com.luxoft.library.repositories;

import com.luxoft.library.entities.Author;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {


    
}
