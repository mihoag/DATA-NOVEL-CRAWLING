package com.hcmus.api.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ResponseData.ResponseAuthor;
import com.hcmus.api.Service.AuthorService;


@RestController
@RequestMapping("/server2")
public class AuthorApi {
     @Autowired
     private AuthorService authorService;
     
     @GetMapping("/tac-gia/{authorId}")
     public ResponseAuthor getDetailAuthor(@PathVariable("authorId") String authorId) throws AuthorNotFoundException
     {
    	 return authorService.getDetailAuthor(authorId);
     }
     
}
