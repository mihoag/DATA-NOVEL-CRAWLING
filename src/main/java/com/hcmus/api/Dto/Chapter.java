package com.hcmus.api.Dto;


import org.springframework.hateoas.RepresentationModel;

public class Chapter extends RepresentationModel<Chapter> {
	private String novelId;
	private String novelName;
	private Integer chapterId;
    private String name;
    private Integer total;
    private Author author;
	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chapter(String novelId, String novelName, Integer chapterId, String name, Integer total, Author author) {
		this.novelId = novelId;
		this.novelName = novelName;
		this.chapterId = chapterId;
		this.name = name;
		this.total = total;
		this.author = author;
	}
	public String getNovelId() {
		return novelId;
	}
	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}
	public String getNovelName() {
		return novelName;
	}
	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
}
