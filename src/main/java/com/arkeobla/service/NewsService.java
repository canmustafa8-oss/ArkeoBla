package com.arkeobla.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    // Google RSS üzerinden haberleri çeker
    public List<Map<String, String>> getGoogleNews(String query) {
        List<Map<String, String>> newsList = new ArrayList<>();
        try {
            String rssUrl = "https://news.google.com/rss/search?q=" +
                    URLEncoder.encode(query, StandardCharsets.UTF_8) +
                    "&hl=tr-TR&gl=TR&ceid=TR:tr";

            Document doc = Jsoup.connect(rssUrl).get();
            Elements items = doc.select("item");

            int count = 0;
            for (Element item : items) {
                if (count >= 5)
                    break; // Sadece ilk 5 haber
                Map<String, String> news = new HashMap<>();
                news.put("title", item.select("title").text());
                news.put("link", item.select("link").text());
                news.put("date", item.select("pubDate").text().substring(0, 16)); // Tarihi kısalt
                newsList.add(news);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    // Hedef URL'deki içeriği klonlar (Reader Mode)
    public Map<String, String> scrapeContent(String url) {
        Map<String, String> content = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            content.put("title", doc.title());

            // Metin içeriğini bulmaya çalış (p etiketleri)
            Elements paragraphs = doc.select("p");
            StringBuilder body = new StringBuilder();

            for (Element p : paragraphs) {
                // Çok pendekileri (reklam vs) atla
                if (p.text().length() > 50) {
                    body.append("<p>").append(p.text()).append("</p>");
                }
            }

            if (body.length() == 0) {
                body.append("<p>İçerik çekilemedi veya korumalı. Lütfen orijinal siteden okuyun.</p>");
                body.append("<a href='" + url + "' target='_blank'>Orijinal Siteye Git</a>");
            }

            content.put("body", body.toString());
            content.put("source", url);

        } catch (IOException e) {
            content.put("title", "Hata");
            content.put("body", "Haber içeriğine ulaşılamadı. Bağlantı ölü olabilir.");
            content.put("source", url);
        }
        return content;
    }
}
