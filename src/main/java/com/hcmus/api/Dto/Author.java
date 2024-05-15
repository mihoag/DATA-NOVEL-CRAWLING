package com.hcmus.api.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.RepresentationModel;

public class Author extends RepresentationModel<Author> {
    private String authorId;
    
    private String name;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Novel> listNovels;
  
    public Author()
    {
    }
	public Author(String authorId, String name, List<Novel> listComics) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.listNovels = listComics;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Novel> getListNovels() {
		return listNovels;
	}
	public void setListNovels(List<Novel> listNovels) {
		this.listNovels = listNovels;
	}
	
}
