package com.hcmus.api.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author extends RepresentationModel<Author> {
    private String authorId;
    
    private String name;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Novel> novels;
}
