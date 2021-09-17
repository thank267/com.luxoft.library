package com.luxoft.library.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO extends AbstractDTO {

    private String text;

    private String bookId;
    private String bookName;

    public CommentDTO(Long bookId) {
        this.bookId = String.valueOf(bookId);
    }
}
