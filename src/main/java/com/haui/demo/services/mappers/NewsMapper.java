package com.haui.demo.services.mappers;

import com.haui.demo.models.entities.News;
import com.haui.demo.models.requests.NewsRq;
import com.haui.demo.models.responses.NewsDetailRp;
import com.haui.demo.models.responses.NewsRp;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News map(News news, NewsRq newsRq) {
        news.setId(newsRq.getId());
        news.setName(newsRq.getName());
        news.setContent(newsRq.getContent());
        news.setTitle(newsRq.getTitle());
        news.setNewsCategory(newsRq.getNewsCategory());
        return news;
    }

    public NewsRp map(News news) {
        NewsRp rp = new NewsRp();
        rp.setId(news.getId());
        rp.setNameNews(news.getName());
        rp.setTitle(news.getTitle());
        rp.setNewsCategory(news.getNewsCategory());
        rp.setCreatedAt(news.getCreatedAt());
        return rp;
    }

    public Page<NewsRp> map(Page<News> news) {
        return news.map(this::map);
    }

    public NewsDetailRp maps(News news) {
        NewsDetailRp newsDetailRp = new NewsDetailRp();
        newsDetailRp.setId(news.getId());
        newsDetailRp.setName(news.getName());
        newsDetailRp.setTitle(news.getTitle());
        newsDetailRp.setContent(news.getContent());
        newsDetailRp.setNewsCategory(news.getNewsCategory());
        return newsDetailRp;
    }

}
