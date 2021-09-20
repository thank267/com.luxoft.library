package com.luxoft.library.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;

@ToString
@Getter
@Setter
public class BookDTO extends AbstractDTO {

    private String name;

    private String authorId;
    private String authorName;

    private String genreId;
    private String genreName;

    private List<CommentDTO> comments = new ArrayList<>();

}
