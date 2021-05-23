package com.haui.demo.services.impls;

import com.haui.demo.models.responses.NewsDetailRp;
import com.haui.demo.models.responses.NewsRssResponse;
import com.haui.demo.services.RssService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssServiceImpl implements RssService {

    public List<NewsRssResponse> readRss(String url) throws IOException, FeedException {
        Logger logger = LoggerFactory.getLogger(RssService.class);
        URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed feed = input.build(new XmlReader(feedUrl));
        List<NewsRssResponse> rssResponses = new ArrayList<>();
        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
            String description = entry.getDescription().getValue();
            NewsRssResponse newsRssResponse = new NewsRssResponse();
            newsRssResponse.setUrl(entry.getLink());
            newsRssResponse.setName(entry.getTitle());
            newsRssResponse.setTitle(getTitle(description));
            newsRssResponse.setImage(getImage(description));
            newsRssResponse.setDate(entry.getPublishedDate());
            rssResponses.add(newsRssResponse);
        }
        return rssResponses;
    }

    private String getImage(String description) {
        int start = description.indexOf("src='");
        int end = description.indexOf("' alt");
        return description.substring(start + 5, end);
    }

    private String getLink(String description) {
        String[] node = description.split("\"");
        return node[1];
    }

    private String getTitle(String description) {
        int index = description.lastIndexOf("</div>");
        return description.substring(index + 6);
    }
}
