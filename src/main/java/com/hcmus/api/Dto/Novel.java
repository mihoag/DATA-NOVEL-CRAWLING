package com.hcmus.api.Dto;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

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
	 
 

	public Novel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Novel(String novelId, String name, String image, Author author, String description, List<Category> category,
			Integer total) {
		super();
		this.novelId = novelId;
		this.name = name;
		this.image = image;
		this.author = author;
		this.description = description;
		this.category = category;
		this.total = total;
	}
	public String getNovelId() {
		return novelId;
	}
	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	
}
