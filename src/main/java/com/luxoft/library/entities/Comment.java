package com.luxoft.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
