package com.hcmus.api.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter extends RepresentationModel<Chapter> {
	private String novelId;
	private String novelName;
	private Integer chapterId;
    private String name;
    private Integer total;
    private Author author;
}
