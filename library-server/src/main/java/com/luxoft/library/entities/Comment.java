package com.luxoft.library.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    @Column(length = 1024, nullable = false, unique = false)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "books_id", nullable = false)
    private Book book;

}
