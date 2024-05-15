package com.hcmus.api.Api;

import com.hcmus.api.ErrorHandler.AuthorNotFoundException;
import com.hcmus.api.ResponseData.ResponseRoot;
import com.hcmus.api.ResponseData.StatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootApi {
    @GetMapping("/")
    public ResponseRoot getApiLinks() throws Exception {
        ResponseRoot res = new ResponseRoot(StatusEnum.success.toString());
        res.add(linkTo(methodOn(NovelApi.class).getListNovels("1")).withRel("Example url to get list novels"));
        res.add(linkTo(methodOn(NovelApi.class).getListChaptersSearchResult("keyword", "1","a-z")).withRel("Example url to get list novels by keyword, order by a-z"));
        res.add(linkTo(methodOn(NovelApi.class).getDetailNovel("novelId")).withRel("Example url to get detail novel"));
        res.add(linkTo(methodOn(ChapterApi.class).getListChapterPerPage("novelId", "1","75")).withRel("Example url to get list chapters by novelId, page = 1, perPage = 75"));
        res.add(linkTo(methodOn(ChapterApi.class).getDetailChapter("novelId", 1)).withRel("Example url to get list chapters by novelId, chapterId = 1"));
        res.add(linkTo(methodOn(AuthorApi.class).getDetailAuthor("1")).withRel("Example url to get detail author"));


        return res;
    }
}
