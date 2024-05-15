package com.hcmus.api.Implement;

import java.util.ArrayList;
import java.util.List;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.hcmus.api.Dto.Author;
import com.hcmus.api.Dto.Category;

import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseDetailNovel;
import com.hcmus.api.ResponseData.ResponseListNovel;
import com.hcmus.api.ResponseData.ResponseNovelSearchResult;
import com.hcmus.api.ResponseData.StatusEnum;
import com.hcmus.api.Utility.AuthorUtility;
import com.hcmus.api.Utility.CategoryUtility;
import com.hcmus.api.Utility.NovelUtility;

public class NovelImpl {
   public static String urlWebsite = "https://truyen.tangthuvien.vn";
		  
   public static ResponseListNovel getListNovel(Integer numberPage) throws Exception
   {
	   List<Novel> lsNovel = new ArrayList<>();
	   Integer totalPage = 0;
	   String url = urlWebsite + "/tong-hop?page=" + numberPage;
	   Integer perPage = 0;
	   try {
		   Document doc = Jsoup.connect(url).get();
		   Element parentElement = doc.getElementsByClass("book-img-text").get(0);
		   
		   
		   // Get total page
		   Elements bookElements =  parentElement.child(0).children();
		   Element totalPageElement = doc.select("ul.pagination").get(0);
		   totalPage = Integer.parseInt(totalPageElement.child(totalPageElement.childrenSize()-2).child(0).text());
		   perPage = bookElements.size();
		   
		   // Initial data
		   String novelId;
		   Author author;
		   String detailNovelUrl;
		   String novelName;
		   Integer totalChapter;
		   String imageURL;
		   String description;  
		   List<Category> categories;
		   
		   
		   for(Element bookElement : bookElements)
		   {
			  detailNovelUrl = bookElement.child(0).child(0).attr("href");  
			  novelId = NovelUtility.getNovelIdFromUrl(detailNovelUrl);
			  imageURL = bookElement.child(0).child(0).child(0).attr("src");
			  novelName = bookElement.child(1).child(0).child(0).text();
			  
			  
			  String authorName = bookElement.child(1).child(1).child(1).text();
			  String authorUrlWebSite =  bookElement.child(1).child(1).child(1).attr("href");
			  String authorId = AuthorUtility.getAuthorIdFromUrl(authorUrlWebSite);
			  author = new Author(authorId, authorName, null);
			  
			  
			  // Category
			  String categoryId = CategoryUtility.getCategoryIdFromUrl( bookElement.child(1).child(1).child(3).attr("href"));
			  String categoryName =  bookElement.child(1).child(1).child(3).text();
			  categories = new ArrayList<>();
			  categories.add(new Category(categoryId, categoryName));
			  
			  
			  totalChapter = Integer.parseInt(bookElement.child(1).child(1).child(7).child(0).text());
			  description =  bookElement.child(1).child(2).html();
			  
			  lsNovel.add(new Novel(novelId, novelName, imageURL, author, description, categories, totalChapter));
		   }  
	   } catch (Exception e) {
		// TODO: handle exception
		   //e.printStackTrace();
		   throw new Exception("Fail when crawling data. The params may not be valid!");
	   }
	   return NovelUtility.addLinks(new ResponseListNovel(StatusEnum.success.toString(), totalPage, numberPage,  null,perPage, NovelUtility.addLinks(lsNovel)));
   }
   
   public static ResponseDetailNovel getDetailNovel(String idNovel) throws Exception {
	    String url = urlWebsite + "/doc-truyen/" + idNovel;
		String name = "";
	    String image = "";
	    
	    Author author = null;
	    String description = "";
	    List<Category> categories = new ArrayList<>();
	    Integer total = 0;
	   
	   try {
	      Document doc = Jsoup.connect(url).get();
		  Element bookInformationElement = doc.select(".book-information.cf").get(0);
	      image = bookInformationElement.child(0).child(0).child(0).attr("src");
	      name = bookInformationElement.child(1).child(0).text();
	      
	      String authorUrl = bookInformationElement.child(1).child(1).child(0).attr("href");
	      String authorName =  bookInformationElement.child(1).child(1).child(0).text();
	      author = new Author(AuthorUtility.getAuthorIdFromUrl(authorUrl), authorName, null);
	      
	      
	      String categoryUrl =  bookInformationElement.child(1).child(1).child(2).attr("href");
	      String categoryName = bookInformationElement.child(1).child(1).child(2).text();
	      categories.add(new Category(CategoryUtility.getCategoryIdFromUrl(categoryUrl), categoryName));
	      
	      Element contentNavElement = doc.select(".content-nav-wrap.cf").get(0);
	      total =  NovelUtility.getTotalChapterFromText(contentNavElement.child(0).child(0).child(1).child(0).text());
      
	      
	      Element bookContentElement = doc.select(".book-content-wrap.cf").get(0);
	      description = bookContentElement.child(0).child(0).child(0).child(0).html();
	} catch (Exception e) {
		throw new NovelNotFoundException("Novel is not found. May be novel id is not valid!");
	}
	   Novel novel =  new Novel(idNovel, name, image, author, description, categories,total);
	   return NovelUtility.addLinks(new ResponseDetailNovel(StatusEnum.success.toString(), novel));
   }
   
  
   public static ResponseNovelSearchResult searchNovelByName(String key, Integer page, String sortBy) throws NovelNotFoundException
   {
	  String url = urlWebsite + "/ket-qua-tim-kiem?term=" + key+ "&page=" + page;
	  Integer totalPage = 1;
        
	  List<Novel> lsNovels = new ArrayList<>();
	  try {
		  Document doc = Jsoup.connect(url).get();
		  Element parentElement = doc.getElementsByClass("book-img-text").get(0);
		   // Get total page
		   try {
			 
			   Element totalPageElement = doc.select("ul.pagination").get(0);
			   totalPage = Integer.parseInt(totalPageElement.child(totalPageElement.childrenSize()-2).child(0).text()); 
	    	} catch (Exception e) {
			// TODO: handle exception
	    	  System.out.println("No such a pagingation element");
		   }
		   Elements bookElements =  parentElement.child(0).children(); 
		   // Initial data
		   String novelId;
		   Author author;
		   String detailNovelUrl;
		   String novelName;
		   Integer totalChapter;
		   String imageURL;
		   String description;  
		   List<Category> categories;

		   for(Element bookElement : bookElements)
		   {
			  detailNovelUrl = bookElement.child(0).child(0).attr("href");  
			  novelId = NovelUtility.getNovelIdFromUrl(detailNovelUrl);
			  imageURL = bookElement.child(0).child(0).child(0).attr("src");
			  novelName = bookElement.child(1).child(0).child(0).text();
			  
			  
			  String authorName = bookElement.child(1).child(1).child(1).text();
			  String authorUrlWebSite =  bookElement.child(1).child(1).child(1).attr("href");
			  String authorId = AuthorUtility.getAuthorIdFromUrl(authorUrlWebSite);
			  author = new Author(authorId, authorName, null);
			  
			  
			  // Category
			  String categoryId = CategoryUtility.getCategoryIdFromUrl( bookElement.child(1).child(1).child(3).attr("href"));
			  String categoryName =  bookElement.child(1).child(1).child(3).text();
			  categories = new ArrayList<>();
			  categories.add(new Category(categoryId, categoryName));
			  
			  
			  totalChapter = Integer.parseInt(bookElement.child(1).child(1).child(7).child(0).text());
			  description =  bookElement.child(1).child(2).html();
			  
			  lsNovels.add(new Novel(novelId, novelName, imageURL, author, description, categories, totalChapter));
		   }  
		  
	} catch (Exception e) {
		// TODO: handle exception
		return NovelUtility.addLinks(new ResponseNovelSearchResult(StatusEnum.error.toString(), 0, page, key, sortBy, new ArrayList<Novel>()));
	}
	  
	
    lsNovels = NovelUtility.getListNovelsSort(lsNovels, "a-z");
    if(sortBy.equals("z-a"))
    {
    	lsNovels = NovelUtility.reverse(lsNovels);
    }
    
	return  NovelUtility.addLinks(new ResponseNovelSearchResult(StatusEnum.success.toString(), totalPage, page, key, sortBy, NovelUtility.addLinks(lsNovels)));
   }

}
