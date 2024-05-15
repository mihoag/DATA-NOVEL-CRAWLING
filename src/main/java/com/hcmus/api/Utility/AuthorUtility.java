package com.hcmus.api.Utility;

import com.hcmus.api.Api.AuthorApi;
import com.hcmus.api.Api.NovelApi;
import com.hcmus.api.Dto.Author;
import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.ResponseData.ResponseAuthor;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
public class AuthorUtility {
   
	public static String  getAuthorIdFromUrl(String url)
	{
		  String[] parts = url.split("\\?");
	        
	        // Split the query parameters by "=" to separate the parameter name from the value
	        String[] queryParams = parts[1].split("=");
	        
	        // The value of the "author" parameter is the second part
	        String authorId = queryParams[1];

	        return authorId;
	}

	public static Author addLinks(Author author) throws AuthorNotFoundException {

		List<Novel> lsNovel = author.getNovels();
		if(lsNovel != null)
		{
			lsNovel.forEach(novel -> {
                try {
                    NovelUtility.addLinks(novel);
                } catch (NovelNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (AuthorNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
		}
		return author;
	}

	public static ResponseAuthor addLinks(ResponseAuthor response) throws AuthorNotFoundException {
		response.add(linkTo(methodOn(AuthorApi.class).getDetailAuthor(response.getData().getAuthorId())).withSelfRel()) ;
		return response;
	}

}
