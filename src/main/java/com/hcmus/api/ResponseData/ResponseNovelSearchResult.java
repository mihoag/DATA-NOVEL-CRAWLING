package com.hcmus.api.ResponseData;
import java.util.List;

import com.hcmus.api.Dto.Novel;

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
  public List<Novel> getData() {
	return data;
  }
  public void setData(List<Novel> data) {
	this.data = data;
  }

  public String getSearchValue() {
	return searchValue;
  }
  public void setSearchValue(String searchValue) {
	this.searchValue = searchValue;
  }

  public String getSortBy() {
	return sortBy;
  }

  public void setSortBy(String sortBy) {
	this.sortBy = sortBy;
  }
  
}
