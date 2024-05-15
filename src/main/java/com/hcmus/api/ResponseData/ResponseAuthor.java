package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.Author;

public class ResponseAuthor extends Response{
   private Author data;
   
   public ResponseAuthor(String status, Author author)
   {
	   super(status);
	   this.data = author;
   }

   public ResponseAuthor() {
	 super();
	// TODO Auto-generated constructor stub
   }

   public Author getData() {
	 return data;
   }

   public void setData(Author data) {
 	this.data = data;
   }
}
