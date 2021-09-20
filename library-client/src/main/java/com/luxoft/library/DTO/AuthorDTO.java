package com.luxoft.library.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class AuthorDTO extends AbstractDTO {

    private String name;

    private List<BookDTO> books = new ArrayList<>();

}
