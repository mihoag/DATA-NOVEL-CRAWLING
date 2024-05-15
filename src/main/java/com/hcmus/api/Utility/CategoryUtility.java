package com.hcmus.api.Utility;


public class CategoryUtility {
   public static String getCategoryIdFromUrl(String url)
   {
	   String[]  paths = url.split("/");
	   return paths[paths.length-1];
   }
}
