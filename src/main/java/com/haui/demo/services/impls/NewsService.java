package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Image;
import com.haui.demo.models.entities.News;
import com.haui.demo.models.entities.NewsCategory;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.NewsRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.models.responses.ImageRp;
import com.haui.demo.models.responses.NewsDetailRp;
import com.haui.demo.models.responses.NewsRp;
import com.haui.demo.repositories.ImageRepository;
import com.haui.demo.repositories.NewsCategoryRepository;
import com.haui.demo.repositories.NewsRepository;
import com.haui.demo.services.INewsService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.NewsMapper;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Service
public class NewsService implements INewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper mapper;
    @Autowired
    private NewsCategoryRepository newsCategoryRepository;
    @Autowired
    private JwtUser jwtUser;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<SystemResponse<Object>> getAll(Panigation panigation) {
        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<News> news = newsRepository.findAll(pageable);
        Page<NewsRp> rpPage = mapper.map(news);
        for (NewsRp newsRp : rpPage) {
            ImageRp image = imageService.loadNewsAvatarImages(newsRp.getId());
            newsRp.setImageRp(image);
        }
        return Response.ok(rpPage);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getAllByStatus(Integer status, Panigation panigation) {

        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<News> news = null;

        if (status.equals(Global.ACTIVE)) {
            news = newsRepository.findByStatus(Global.ACTIVE, pageable);
        }

        if (status.equals(Global.NOACTIVE)) {
            news = newsRepository.findByStatus(Global.NOACTIVE, pageable);
        }

        assert news != null;
        Page<NewsRp> rpPage = mapper.map(news);
        for (NewsRp newsRp : rpPage) {
            ImageRp image = imageService.loadNewsAvatarImages(newsRp.getId());
            newsRp.setImageRp(image);
        }
        return Response.ok(rpPage);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getAllByCategory(String id, Panigation panigation) {
        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<News> news = null;
        if (id != null)
            news = newsRepository.findByNewsCategoryAndStatus(id, Global.ACTIVE, pageable);
        else {
            news = newsRepository.findByStatus(Global.ACTIVE, pageable);
        }
        Page<NewsRp> rpPage = mapper.map(news);
        for (NewsRp newsRp : rpPage) {
            ImageRp image = imageService.loadNewsAvatarImages(newsRp.getId());
            newsRp.setImageRp(image);
        }
        return Response.ok(rpPage);

    }

    @Override
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, NewsRq newsRq) {
        NewsCategory newsCategory = newsCategoryRepository.findById(newsRq.getNewsCategory()).orElse(null);
        if (Objects.isNull(newsCategory)) {
            return Response.badRequest(StringResponse.NEWS_CATEGORY_IS_FAKE);
        }
        News news = new News();
        news = mapper.map(news, newsRq);

        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            news.setCreatedBy(user.getId());
        }
        news.setStatus(Global.ACTIVE);
        newsRepository.save(news);
        List<ImageRp> image = imageService.saveImage(news.getId(), Global.NEWS, newsRq.getImages());
        NewsDetailRp newsDetailRp = mapper.maps(news);
        newsDetailRp.setImageRqs(image);
        return Response.ok(newsDetailRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, NewsRq newsRq) {
        News news = newsRepository.findById(newsRq.getId()).orElse(null);
        if (Objects.isNull(news)) {
            return Response.badRequest(StringResponse.NEWS_IS_FAKE);
        }
        NewsCategory newsCategory = newsCategoryRepository.findById(newsRq.getNewsCategory()).orElse(null);
        if (Objects.isNull(newsCategory)) {
            return Response.badRequest(StringResponse.NEWS_CATEGORY_IS_FAKE);
        }
        news = mapper.map(news, newsRq);

        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            news.setUpdatedBy(user.getId());
        }

        news.setStatus(Global.ACTIVE);
        newsRepository.save(news);
        imageRepository.deleteByNews(news.getId());
        List<ImageRp> image = imageService.saveImage(news.getId(), Global.NEWS, newsRq.getImages());
        NewsDetailRp newsDetailRp = mapper.maps(news);
        newsDetailRp.setImageRqs(image);
        return Response.ok(newsDetailRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq) {
        News news = newsRepository.findById(statusRq.getId()).orElse(null);
        if (Objects.isNull(news)) {
            return Response.badRequest(StringResponse.NEWS_IS_FAKE);
        }
        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            news.setUpdatedBy(user.getId());
        }
        if (statusRq.getStatus().equals(Global.ACTIVE)) {
            news.setStatus(Global.ACTIVE);
        }

        if (statusRq.getStatus().equals(Global.NOACTIVE)) {
            news.setStatus(Global.NOACTIVE);
        }
        newsRepository.save(news);
        return Response.ok(StringResponse.SUCCESS);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, String id) {
        News news = newsRepository.findById(id).orElse(null);
        if (Objects.isNull(news)) {
            return Response.badRequest(StringResponse.NEWS_IS_FAKE);
        }
        List<ImageRp> imageRps = imageService.loadNewsImages(id);
        NewsDetailRp newsDetailRp = mapper.maps(news);
        newsDetailRp.setImageRqs(imageRps);
        return Response.ok(newsDetailRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> delOne(HttpServletRequest request, String id) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) return Response.badRequest(StringResponse.NEWS_IS_FAKE);

        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            news.setUpdatedBy(user.getId());
        }
        news.setStatus(Global.NOACTIVE);
        newsRepository.save(news);
        return Response.ok();
    }
}
