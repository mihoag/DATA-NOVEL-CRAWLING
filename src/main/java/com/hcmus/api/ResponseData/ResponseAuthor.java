package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthor extends Response{
   private Author data;
   
   public ResponseAuthor(String status, Author author)
   {
	   super(status);
	   this.data = author;
   }
}
