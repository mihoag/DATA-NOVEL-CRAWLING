package com.hcmus.api.Service;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import com.hcmus.api.ErrorHandler.ChapterNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.Implement.ChapterImpl;
import com.hcmus.api.ResponseData.ResponseDetailChapter;
import com.hcmus.api.ResponseData.ResponseListChapter;

@Service
public class ChapterService {
	
	
    public ResponseListChapter getListChapter(String idNovel, Integer page, Integer limit) throws NovelNotFoundException, AuthorNotFoundException {
    	return ChapterImpl.getListChapters(idNovel, page, limit);
    }
    
    public ResponseDetailChapter getDetailChapter(String idNovel, Integer chapterId) throws NovelNotFoundException, ChapterNotFoundException, AuthorNotFoundException {
    	return ChapterImpl.getDetailChapter(idNovel, chapterId);
    }
}
