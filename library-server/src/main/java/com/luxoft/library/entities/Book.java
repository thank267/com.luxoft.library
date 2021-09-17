package com.luxoft.library.entities;

import com.luxoft.library.BookProto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    public static Book fromProto(BookProto proto) {
        Book entry = new Book();
        entry.setId(proto.getId());
        entry.setName(proto.getName());
        entry.setAuthor(Author.fromProto(proto.getAuthor()));
        entry.setGenre(Genre.fromProto(proto.getGenre()));
        return entry;
    }

    public BookProto toProto() {
        return BookProto.newBuilder()
                .setId(getId())
                .setName(getName())
                .setAuthor(getAuthor().toProto())
                .setGenre(getGenre().toProto())
                .build();
    }

}
