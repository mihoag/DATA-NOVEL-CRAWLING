package com.hcmus.api.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailNovel extends Novel{
    
	List<Chapter> chapters;
    public DetailNovel(String novelId, String name, String image, Author author, String description, List<Category> category,
			Integer total, List<Chapter> chapters)
    {
    	super(novelId, name, image, author, description, category, total);
    	this.chapters = chapters;
    }
}
