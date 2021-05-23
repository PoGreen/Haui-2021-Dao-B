package com.haui.demo.services;

import com.haui.demo.models.responses.NewsRssResponse;
import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.util.List;

public interface RssService {

    List<NewsRssResponse> readRss(String url) throws IOException, FeedException;
}
