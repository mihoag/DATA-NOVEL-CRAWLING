package com.hcmus.api.ResponseData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hcmus.api.Dto.Novel;

public class ResponseListNovel  extends Response{
   private List<Novel> data;
   private Integer totalPage;
   private Integer currentPage;
   private Integer perPage;
   
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String searchValue;
   
   
   
   public ResponseListNovel()
   {
   }
   
   public ResponseListNovel(String status, Integer totalPage, Integer currentPage, String searchValue, Integer perPage, List<Novel> data)
   {
	   super(status);
	   this.totalPage = totalPage;
	   this.currentPage = currentPage;
	   this.searchValue = searchValue;
	   this.perPage = perPage;
	   this.data = data;
   }

   public List<Novel> getData() {
	 return data;
   }

   public void setData(List<Novel> data) {
	this.data = data;
   }

   public Integer getTotalPage() {
	return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
  }

  public Integer getCurrentPage() {
	return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
  }

  public String getSearchValue() {
	return searchValue;
  }

  public void setSearchValue(String searchValue) {
	this.searchValue = searchValue;
  }

  public Integer getPerPage() {
	return perPage;
  }

  public void setPerPage(Integer perPage) {
	this.perPage = perPage;
  }
   
  
   
   
}
