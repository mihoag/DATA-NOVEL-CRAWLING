package com.hcmus.api.Dto;

public class Category {
   private String categoryId;
   private String name;
   
   public String getCategoryId() {
	 return categoryId;
   }
   
    
   
  public Category(String categoryId, String name) {
	super();
	this.categoryId = categoryId;
	this.name = name;
  }

  public void setCategoryId(String categoryId) {
	this.categoryId = categoryId;
  }
  
  public String getName() {
	return name;
  }
  
  public void setName(String name) {
	 this.name = name;
  } 
}
