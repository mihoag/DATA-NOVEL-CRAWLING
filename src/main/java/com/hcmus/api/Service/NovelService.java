package com.hcmus.api.Service;

import java.util.ArrayList;
import java.util.List;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.Implement.NovelImpl;
import com.hcmus.api.ResponseData.ResponseDetailNovel;
import com.hcmus.api.ResponseData.ResponseListNovel;
import com.hcmus.api.ResponseData.ResponseNovelSearchResult;
import com.hcmus.api.ResponseData.StatusEnum;

@Service
public class NovelService {
     
	public ResponseListNovel getListNovelPerPage(Integer page) throws Exception
	{
		return NovelImpl.getListNovel(page);
	}
	
	public ResponseDetailNovel getDetailNovel(String id) throws Exception {
		return NovelImpl.getDetailNovel(id);
	}
	
	public ResponseNovelSearchResult getListNovelSearchResult(String key, Integer page, String sortBy) throws NovelNotFoundException
	{
		return NovelImpl.searchNovelByName(key, page, sortBy);
	}
}
