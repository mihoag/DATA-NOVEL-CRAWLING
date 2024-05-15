package com.hcmus.api.Utility;

import com.hcmus.api.Api.AuthorApi;
import com.hcmus.api.Api.ChapterApi;
import com.hcmus.api.Dto.Chapter;
import com.hcmus.api.Dto.DetailChapter;
import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ErrorHandler.ChapterNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseDetailChapter;
import com.hcmus.api.ResponseData.ResponseListChapter;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
public class ChapterUtility {
   public static Integer getChapterIdFromUrl(String url)
   {
	   String[] parts = url.split("/");
	   String[] parts2 = parts[parts.length-1].split("-");
	   return Integer.parseInt(parts2[parts2.length-1]);
   }
   
   public static Integer calculateTotalPage(Integer totalElements, Integer numPerPage)
   {
	   if(totalElements % numPerPage == 0)
	   {
		   return totalElements / numPerPage;
	   }
	   return totalElements/numPerPage + 1;
   }

   public static Chapter addLinks(Chapter chapter) throws ChapterNotFoundException, NovelNotFoundException, AuthorNotFoundException {
	   chapter.add(linkTo(methodOn(ChapterApi.class).getDetailChapter(chapter.getNovelId(),chapter.getChapterId())).withRel("detailChapterUrl"));
	   return chapter;
   }

   public static List<Chapter> addLinks(List<Chapter> lsChapers)
   {
	   lsChapers.forEach(chapter -> {
           try {
               ChapterUtility.addLinks(chapter);
           } catch (ChapterNotFoundException e) {
               throw new RuntimeException(e);
           } catch (NovelNotFoundException e) {
               throw new RuntimeException(e);
           } catch (AuthorNotFoundException e) {
               throw new RuntimeException(e);
           }
       });
	   return lsChapers;
   }

   public static ResponseListChapter addLinks(ResponseListChapter response) throws NovelNotFoundException, AuthorNotFoundException {
	   response.add(linkTo(methodOn(ChapterApi.class).getListChapterPerPage(response.getData().get(0).getNovelId(), response.getCurrentPage()+"", response.getPerPage()+"")).withSelfRel());
       response.add(linkTo(methodOn(AuthorApi.class).getDetailAuthor(response.getData().get(0).getAuthor().getAuthorId())).withRel("detailAuthorUrl"));
	   return response;
   }

   public static ResponseDetailChapter addLinks(ResponseDetailChapter response) throws NovelNotFoundException, ChapterNotFoundException, AuthorNotFoundException {
	   response.add(linkTo(methodOn(ChapterApi.class).getDetailChapter(response.getData().getNovelId(), response.getData().getChapterId())).withSelfRel());
       response.add(linkTo(methodOn(ChapterApi.class).getListChapterPerPage(response.getData().getNovelId(), "1","75")).withRel("listChaptersUrl"));
	   return response;
   }
}

