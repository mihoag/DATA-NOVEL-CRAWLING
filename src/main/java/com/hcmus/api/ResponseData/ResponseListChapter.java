package com.hcmus.api.ResponseData;

import java.util.List;

import com.hcmus.api.Dto.Chapter;
public class ResponseListChapter extends Response {
  
	
   private Integer currentPage;
   private Integer totalPage;
   private Integer perPage;
   
   private List<Chapter> data;
   
   
   
   public ResponseListChapter() {
	super();
	// TODO Auto-generated constructor stub
   }
  
   
   

   public ResponseListChapter(String status, Integer currentPage, Integer totalPage, Integer perPage, List<Chapter> data) {
	 super(status);
	 this.currentPage = currentPage;
	 this.totalPage = totalPage;
	 this.perPage = perPage;
	 this.data = data;
   }
  
   public Integer getCurrentPage() {
	 return currentPage;
   }
   public void setCurrentPage(Integer currentPage) {
	 this.currentPage = currentPage;
   }
   public Integer getTotalPage() {
	 return totalPage;
   }
   public void setTotalPage(Integer totalPage) {
	 this.totalPage = totalPage;
   }
   public Integer getPerPage() {
	 return perPage;
   }
   public void setPerPage(Integer perPage) {
	 this.perPage = perPage;
   }
   public List<Chapter> getData() {
    	return data;
   }
   public void setData(List<Chapter> data) {
	 this.data = data;
   }
}
