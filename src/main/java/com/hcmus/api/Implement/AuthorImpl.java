package com.hcmus.api.Implement;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hcmus.api.Dto.Author;
import com.hcmus.api.Dto.Category;
import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ResponseData.ResponseAuthor;
import com.hcmus.api.ResponseData.ResponseListNovel;
import com.hcmus.api.ResponseData.StatusEnum;
import com.hcmus.api.Utility.AuthorUtility;
import com.hcmus.api.Utility.CategoryUtility;
import com.hcmus.api.Utility.NovelUtility;

public class AuthorImpl {
	 public static String urlWebsite = "https://truyen.tangthuvien.vn";
	 public static ResponseAuthor getDetailAuthor(String authorId) throws AuthorNotFoundException 
	   {
		   List<Novel> lsNovel = new ArrayList<>();
		   String url = urlWebsite + "/tac-gia?author=" + authorId;
		   
		   String authorName;
		   
		   try {
			   Document doc = Jsoup.connect(url).get();
			   Element authorPhotoElement = doc.getElementById("authorId");
			   authorName = authorPhotoElement.child(1).child(0).text();
			   
			   Element bookImgTextElement = doc.getElementsByClass("book-img-text").get(0);
			   Elements bookElements = bookImgTextElement.child(0).children();
			   for(Element bookElement : bookElements)
			   {
				   String novelId;
				   Author author;
				   String novelName;
				   Integer totalChapter;
				   String imageURL;
				   String description;  
				   List<Category> categories = new ArrayList<>();
						   ;
				   if(bookElement.childrenSize() > 1)
				   {
					  imageURL =bookElement.child(0).child(0).child(0).attr("src");
					  String novelUrl  = bookElement.child(0).child(0).attr("href");
					  novelId = NovelUtility.getNovelIdFromUrl(novelUrl);
					  novelName = bookElement.child(1).child(0).text();
					  author = new Author(authorId, authorName,null);
					  String categoryId = CategoryUtility.getCategoryIdFromUrl(bookElement.child(1).child(1).child(3).attr("href"));
					  String categoryName = bookElement.child(1).child(1).child(3).text();
					  categories.add(new Category(categoryId, categoryName));
					  description = bookElement.child(1).child(2).html();
					  totalChapter = Integer.parseInt(bookElement.child(1).child(1).child(7).child(0).text());
					  lsNovel.add(new Novel(novelId, novelName, imageURL, author, description, categories, totalChapter));  
				   }
			   }
		   }
		   catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
			   throw new AuthorNotFoundException("Author is not found");
	       }
		  
		   return new ResponseAuthor(StatusEnum.success.toString(), AuthorUtility.addLinks(new Author(authorId, authorName, lsNovel)));
	   }

}
