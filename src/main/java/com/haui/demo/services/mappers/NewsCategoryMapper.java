package com.haui.demo.services.mappers;


import com.haui.demo.models.entities.NewsCategory;
import com.haui.demo.models.requests.NewsCategoryRq;
import com.haui.demo.models.responses.NewsCategoryRp;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsCategoryMapper {

    public List<NewsCategoryRp> map(List<NewsCategory> newsCategories){
        return newsCategories.stream().map(this::map).collect(Collectors.toList());
    }

    public NewsCategory map(NewsCategoryRq newsCategoryRq){
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setId(newsCategoryRq.getId());
        newsCategory.setName(newsCategoryRq.getName());
        newsCategory.setDescription(newsCategoryRq.getDescription());
        return newsCategory;
    }

    public NewsCategoryRp map(NewsCategory newsCategory){
        NewsCategoryRp rp = new NewsCategoryRp();
        rp.setId(newsCategory.getId());
        rp.setName(newsCategory.getName());
        rp.setDescription(newsCategory.getDescription());
        rp.setStatus(newsCategory.getStatus());
        rp.setCreatedAt(newsCategory.getCreatedAt());
        return  rp;
    }
}
