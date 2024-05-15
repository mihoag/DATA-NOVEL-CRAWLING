package com.hcmus.api.Service;

import org.springframework.stereotype.Service;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.Implement.AuthorImpl;
import com.hcmus.api.ResponseData.ResponseAuthor;

@Service
public class AuthorService {
     
	
	public ResponseAuthor getDetailAuthor(String authorId) throws AuthorNotFoundException
	{
		return AuthorImpl.getDetailAuthor(authorId);
	}
}
