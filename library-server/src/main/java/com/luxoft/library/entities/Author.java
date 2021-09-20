package com.luxoft.library.entities;

import com.luxoft.library.AuthorProto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends AbstractEntity {

    @Column(length = 250, nullable = false, unique = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public static Author fromProto(AuthorProto proto) {
        Author entry = new Author();
        entry.setId(proto.getId());
        entry.setName(proto.getName());
        return entry;
    }

    public AuthorProto toProto() {
        return AuthorProto.newBuilder()
                .setId(getId())
                .setName(getName())
                .build();
    }

}
