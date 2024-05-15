package com.hcmus.api.Api;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseDetailNovel;
import com.hcmus.api.ResponseData.ResponseListNovel;
import com.hcmus.api.ResponseData.ResponseNovelSearchResult;
import com.hcmus.api.Service.NovelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/server2")
public class NovelApi {
	
   @Autowired
   private NovelService novelSer;
	
	
   @GetMapping("/ds-truyen")
   public ResponseListNovel getListNovels(@RequestParam(name = "page", defaultValue = "1") String page) throws NumberFormatException, Exception {
	   return novelSer.getListNovelPerPage(Integer.parseInt(page));
   }   
   
   @GetMapping("/truyen/{novelId}")
   public ResponseDetailNovel getDetailNovel(@PathVariable("novelId") String novelId) throws Exception {
	   System.out.println(novelId);
	   return novelSer.getDetailNovel(novelId);
   }
   
   @GetMapping("/tim-kiem")
   public ResponseNovelSearchResult getListChaptersSearchResult(@RequestParam("key") String key, @RequestParam(name =  "page", defaultValue = "1") String page, @RequestParam(name = "orderBy", defaultValue = "a-z") String sortBy) throws NumberFormatException, NovelNotFoundException
   {
	   try {
		   return novelSer.getListNovelSearchResult(key, Integer.parseInt(page), sortBy);
	   } catch (Exception e) {
		// TODO: handle exception
		   throw new NumberFormatException("Page value is not valid!");
	   }
   }
}
