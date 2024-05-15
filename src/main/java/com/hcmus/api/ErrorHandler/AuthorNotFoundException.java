package com.hcmus.api.ErrorHandler;

public class AuthorNotFoundException extends Exception {
   public AuthorNotFoundException(String msg)
   {
	   super(msg);
   }
}
