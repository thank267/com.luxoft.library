package com.luxoft.library.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

    @Column(length = 250, nullable = false, unique = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "authors_id", nullable = false)
    private Author author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genres_id", nullable = false)
    private Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

}
