package com.luxoft.library.repositories;

import com.luxoft.library.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {


    
}
