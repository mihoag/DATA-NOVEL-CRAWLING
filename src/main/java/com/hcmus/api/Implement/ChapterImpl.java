package com.hcmus.api.Implement;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hcmus.api.Dto.Author;
import com.hcmus.api.Dto.Category;
import com.hcmus.api.Dto.Chapter;
import com.hcmus.api.Dto.DetailChapter;
import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.ChapterNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseDetailChapter;
import com.hcmus.api.ResponseData.ResponseDetailNovel;
import com.hcmus.api.ResponseData.ResponseListChapter;
import com.hcmus.api.ResponseData.StatusEnum;
import com.hcmus.api.Utility.AuthorUtility;
import com.hcmus.api.Utility.CategoryUtility;
import com.hcmus.api.Utility.ChapterUtility;
import com.hcmus.api.Utility.NovelUtility;

public class ChapterImpl  {
   public static String urlWebsite = "https://truyen.tangthuvien.vn";
   
   public static  ResponseListChapter getListChapters(String idNovel, Integer page, Integer limit) throws NovelNotFoundException, AuthorNotFoundException {
	     String url = urlWebsite + "/doc-truyen/" + idNovel;
		 String name = "";
	     Author author = null;
	     Integer total = 0;
	     Integer secreteId = 0;
	     List<Chapter> lsChapters = null;
	   
	     try {
	      Document doc = Jsoup.connect(url).get();
		  Element bookInformationElement = doc.select(".book-information.cf").get(0);
	      name = bookInformationElement.child(1).child(0).text();
	      
	      String authorUrl = bookInformationElement.child(1).child(1).child(0).attr("href");
	      String authorName =  bookInformationElement.child(1).child(1).child(0).text();
	      author = new Author(AuthorUtility.getAuthorIdFromUrl(authorUrl), authorName, null);
	      
	      
	 
	      Element contentNavElement = doc.select(".content-nav-wrap.cf").get(0);
	      total =  NovelUtility.getTotalChapterFromText(contentNavElement.child(0).child(0).child(1).child(0).text());
          
	      Element storyHiddenElement = doc.getElementById("story_id_hidden");
	      secreteId = Integer.parseInt(storyHiddenElement.val());
	      
	     
	      System.out.println(secreteId);
	      
	      lsChapters = ChapterImpl.getListChapterImpl(page, limit, secreteId, name, author, total, idNovel);
	   	    
	  } catch (Exception e) {
		e.printStackTrace();
		throw new NovelNotFoundException("Author is not found");
	 }
	  // Calculate total page
	  Integer totalPage = ChapterUtility.calculateTotalPage(total, limit);
	  return ChapterUtility.addLinks(new ResponseListChapter(StatusEnum.success.toString(), page, totalPage, limit, lsChapters));
   }
   
   private static List<Chapter> getListChapterImpl(int page, int limit, int secreteId, String novelName, Author author, Integer total, String novelId) throws Exception
   {
	   List<Chapter> lsChapters = new ArrayList<>();
	   String url = "https://truyen.tangthuvien.vn/doc-truyen/page/" +secreteId+"?page=" +(page-1)+"&limit="+ limit;
	   try {
	    Document doc = Jsoup.connect(url).get();
		Elements chapterElements = doc.getElementsByTag("li");
		int count = 0;
		for(Element chapterElement : chapterElements)
		{
			if(chapterElement.hasClass("divider-chap") || chapterElement.childrenSize() ==1)
			{
				continue;
			}
		
			count++;
			System.out.println("Count : " + (page-1)*limit + count  + " , Total : "+ total );
			if(count> limit || (page-1)*limit + count > total)
			{
				break;
			}
			System.out.println(count + " : " + chapterElement.childrenSize());
			lsChapters.add(new Chapter(novelId, novelName, ChapterUtility.getChapterIdFromUrl(chapterElement.child(1).attr("href")), chapterElement.child(1).child(0).text(), total, author));
		}
		System.out.println(chapterElements.size());
	   } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new Exception("Fail when get chapters with page = " +page + " and limit " + limit);
	    }
	   return ChapterUtility.addLinks(lsChapters);
   }
   
   
   public static ResponseDetailChapter getDetailChapter(String novelId, Integer chapterId) throws NovelNotFoundException, ChapterNotFoundException, AuthorNotFoundException {
	   String novelName = "";
	   Author author;
	   String url1 = "";
	   String url2 = "";
	   String chapterName = "";
	   Integer total = 0;
	   String content = "";
	   try {
		   url1 = urlWebsite + "/doc-truyen/" + novelId;
		   Document doc = Jsoup.connect(url1).get();
		   Element bookInformationElement = doc.select(".book-information.cf").get(0);
		   novelName = bookInformationElement.child(1).child(0).text();
		      
		   String authorUrl = bookInformationElement.child(1).child(1).child(0).attr("href");
		   String authorName =  bookInformationElement.child(1).child(1).child(0).text();
		   author = new Author(AuthorUtility.getAuthorIdFromUrl(authorUrl), authorName, null);
		   
		   Element contentNavElement = doc.select(".content-nav-wrap.cf").get(0);
		   total =  NovelUtility.getTotalChapterFromText(contentNavElement.child(0).child(0).child(1).child(0).text());
		   
	  } catch (Exception e) {
		// TODO: handle exception
		  throw new NovelNotFoundException("Novel not found!");
	  }      
	    
	   try {
		url2 = url1 + "/chuong-" + chapterId;
		System.out.println( "url2: " + url2);
		Document doc = Jsoup.connect(url2).get();
		Element novelTitleElement = doc.select(".col-xs-12.chapter").get(0);
	    chapterName = novelTitleElement.child(1).text();
		content = doc.select(".box-chap").get(0).text();
	   } catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
		   throw new ChapterNotFoundException("Chapter not found");
	   }
	   return ChapterUtility.addLinks(new ResponseDetailChapter(StatusEnum.success.toString(), new DetailChapter(novelId, novelName, chapterId, chapterName, total, AuthorUtility.addLinks(author), content)));
   }

}
