package com.hcmus.api.ResponseData;
import java.util.List;

import com.hcmus.api.Dto.Novel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNovelSearchResult extends Response{
   private Integer totalPage;
   private Integer currentPage;
   private String searchValue;
   private String sortBy;
   private List<Novel> data;
   
   public ResponseNovelSearchResult(String status, Integer totalPage, Integer currentPage, String searchValue, String sortBy, List<Novel> data)
   {
	   super(status);
	   this.totalPage = totalPage;
	   this.currentPage = currentPage;
	   this.data = data;
	   this.searchValue = searchValue;
	   this.sortBy = sortBy;
   }
}
