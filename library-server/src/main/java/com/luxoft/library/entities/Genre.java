package com.luxoft.library.entities;

import com.luxoft.library.GenreProto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre extends AbstractEntity {

    @Column(length = 250, nullable = false, unique = false)
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public static Genre fromProto(GenreProto proto) {
        Genre entry = new Genre();
        entry.setId(proto.getId());
        entry.setName(proto.getName());
        return entry;
    }

    public GenreProto toProto() {
        return GenreProto.newBuilder()
                .setId(getId())
                .setName(getName())
                .build();
    }

}
