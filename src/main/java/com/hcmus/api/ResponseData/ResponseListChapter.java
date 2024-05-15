package com.hcmus.api.ResponseData;

import java.util.List;

import com.hcmus.api.Dto.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListChapter extends Response {
   private Integer currentPage;
   private Integer totalPage;
   private Integer perPage;
   
   private List<Chapter> data;
   public ResponseListChapter(String status, Integer currentPage, Integer totalPage, Integer perPage, List<Chapter> data) {
	 super(status);
	 this.currentPage = currentPage;
	 this.totalPage = totalPage;
	 this.perPage = perPage;
	 this.data = data;
   }
}
