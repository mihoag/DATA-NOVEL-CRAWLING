package com.hcmus.api.Utility;

import java.util.List;

import com.hcmus.api.Api.AuthorApi;
import com.hcmus.api.Api.ChapterApi;
import com.hcmus.api.Api.NovelApi;
import com.hcmus.api.Dto.Novel;
import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ErrorHandler.NovelNotFoundException;
import com.hcmus.api.Implement.NovelImpl;
import com.hcmus.api.ResponseData.ResponseDetailNovel;
import com.hcmus.api.ResponseData.ResponseListNovel;
import com.hcmus.api.ResponseData.ResponseNovelSearchResult;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class NovelUtility {
    public static String getNovelIdFromUrl(String url)
    {
          String[] components = url.split("/");
          return components[components.length-1];
    }
    
    public static String getRealNovelDetailUrl(String url, String hostUrl)
    {
        String realUrl =  url.replace(NovelImpl.urlWebsite, hostUrl);
        return realUrl;
    }
    
    public static Integer getTotalChapterFromText(String text)
    {
    	 String[] parts = text.split("[()]");
         // Extract the number
         String numberString = parts[1].trim().split(" ")[0]; // Get the second part and remove leading/trailing spaces
         // Parse the number
         //System.out.println(numberString);
         return Integer.parseInt(numberString);	
    }
    
    public static List<Novel> getListNovelsSort(List<Novel> lsNovels, String sortBy)
    {
    	   
    	Integer sortCondition = sortBy.compareTo("a-z") == 0 ? 1  : -1;
    	   
    	   
           boolean swapped;
           int size = lsNovels.size();
           for (int i = 0; i < size - 1; i++) {
               swapped = false;
               for (int j = 0; j <  size - i - 1; j++) {
                   if (lsNovels.get(j).getName().compareTo(lsNovels.get(j+1).getName()) == sortCondition) {
                	   System.out.println(lsNovels.get(j).getName().compareTo(lsNovels.get(j+1).getName()));
                       // Swap list[j] and list[j + 1]
                       Novel temp = lsNovels.get(j);
                       lsNovels.set(j, lsNovels.get(j + 1));
                       lsNovels.set(j + 1, temp);
                       swapped = true;
                   }
               }
               // If no two elements were swapped in the inner loop, break
               if (!swapped) break;
           }
           
           for(Novel n : lsNovels)
           {
        	   System.out.println(n.getName());
           }
           
           return lsNovels;
    }
    
    public static List<Novel> reverse(List<Novel> lsNovels)
    {
    	 int start = 0;
         int end = lsNovels.size() - 1;
         
         while (start < end) {
             // Swap elements at start and end indices
             Novel temp = lsNovels.get(start);
             lsNovels.set(start, lsNovels.get(end));
             lsNovels.set(end, temp);
             
             // Move towards the center of the list
             start++;
             end--;
         }
         return lsNovels;
    }

    public static Novel addLinks(Novel novel) throws Exception {
        novel.add(linkTo(methodOn(NovelApi.class).getDetailNovel(novel.getNovelId())).withSelfRel());
        novel.add(linkTo(methodOn(AuthorApi.class).getDetailAuthor(novel.getAuthor().getAuthorId())).withRel("detailAuthorUrl"));
        return novel;
    }
    public static ResponseDetailNovel addLinks(ResponseDetailNovel detailNovel) throws Exception {
        detailNovel.add(linkTo(methodOn(NovelApi.class).getDetailNovel(detailNovel.getData().getNovelId())).withSelfRel());
        detailNovel.add(linkTo(methodOn(AuthorApi.class).getDetailAuthor(detailNovel.getData().getAuthor().getAuthorId())).withRel("detailAuthorUrl"));
        detailNovel.add(linkTo(methodOn(NovelApi.class).getListNovels("1")).withRel("listNovelsUrl"));
        detailNovel.add(linkTo(methodOn(ChapterApi.class).getListChapterPerPage(detailNovel.getData().getNovelId(), "1", "75")).withRel("listChaptersUrl"));

        return detailNovel;
    }

    public static List<Novel> addLinks(List<Novel> novels)  {
        novels.forEach(novel -> {
            try {
                addLinks(novel);
            } catch (NovelNotFoundException e) {
                throw new RuntimeException(e);
            } catch (AuthorNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return novels;
    }

    public static ResponseNovelSearchResult addLinks(ResponseNovelSearchResult response) throws NovelNotFoundException {
        response.add(linkTo(methodOn(NovelApi.class).getListChaptersSearchResult(response.getSearchValue(), response.getCurrentPage() + "", response.getSortBy())).withSelfRel());
        return response;
    }

    public static ResponseListNovel addLinks(ResponseListNovel response) throws Exception {
        response.add(linkTo(methodOn(NovelApi.class).getListNovels(response.getCurrentPage() + "")).withSelfRel());
        return response;
    }
}
