package com.hcmus.api.Dto;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Novel extends RepresentationModel<Novel>{
	
	protected String novelId;
	
    protected String name;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String image;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Author author;
    
   
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String description;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<Category> category;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer total;

}
