package com.hcmus.api.Dto;

public class DetailChapter extends Chapter {
    private String content;

    public DetailChapter()
    {
    }
    
	public DetailChapter(String novelId, String novelName, Integer chapterId, String name, Integer total,
			Author author, String content) {
		super(novelId, novelName, chapterId, name, total, author);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}     
}
