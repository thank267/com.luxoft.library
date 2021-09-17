package com.luxoft.library.repositories;

import com.luxoft.library.entities.Comment;

import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends AbstractJpaDAO<Comment, Long> {

    public CommentRepository() {
        setClazz(Comment.class);
    }
}
