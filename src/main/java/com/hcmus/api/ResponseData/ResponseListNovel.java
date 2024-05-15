package com.hcmus.api.ResponseData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hcmus.api.Dto.Novel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListNovel  extends Response{
   private List<Novel> data;
   private Integer totalPage;
   private Integer currentPage;
   private Integer perPage;
   
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String searchValue;
   
   public ResponseListNovel(String status, Integer totalPage, Integer currentPage, String searchValue, Integer perPage, List<Novel> data)
   {
	   super(status);
	   this.totalPage = totalPage;
	   this.currentPage = currentPage;
	   this.searchValue = searchValue;
	   this.perPage = perPage;
	   this.data = data;
   }
}
