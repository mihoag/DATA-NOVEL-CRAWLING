package com.hcmus.api.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailChapter extends Chapter {
    private String content;
	public DetailChapter(String novelId, String novelName, Integer chapterId, String name, Integer total,
			Author author, String content) {
		super(novelId, novelName, chapterId, name, total, author);
		this.content = content;
	}
}
