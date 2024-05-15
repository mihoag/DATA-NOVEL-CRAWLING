package com.hcmus.api.Api;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcmus.api.ErrorHandler.ChapterNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseDetailChapter;
import com.hcmus.api.ResponseData.ResponseListChapter;
import com.hcmus.api.Service.ChapterService;

@RestController
@RequestMapping("/server2")
public class ChapterApi {
	
    @Autowired
    private ChapterService chapterService;
    
    @GetMapping("/truyen/{novelId}/chapters")  
    public ResponseListChapter getListChapterPerPage(@PathVariable("novelId") String id, @RequestParam(name =  "page", defaultValue = "1") String page, @RequestParam(name =  "perPage", defaultValue = "75") String limit) throws NumberFormatException, NovelNotFoundException, AuthorNotFoundException {
    	return chapterService.getListChapter(id, Integer.parseInt(page), Integer.parseInt(limit));
    }
    
    @GetMapping("/truyen/{novelId}/{chapterId}")
    public ResponseDetailChapter getDetailChapter(@PathVariable("novelId") String novelId, @PathVariable("chapterId") Integer chapterId) throws NovelNotFoundException, ChapterNotFoundException, AuthorNotFoundException {
    	System.out.println(chapterId);
    	return chapterService.getDetailChapter(novelId, chapterId);
    }
}
