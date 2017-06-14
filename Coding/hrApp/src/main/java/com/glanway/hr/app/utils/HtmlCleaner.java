package com.glanway.hr.app.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Whitelist;
import org.springframework.util.StringUtils;

/**
 */
public abstract class HtmlCleaner {
    public static final String PLACEHOLDER_BASE_URI = "http://www.vacoor.com";

    public static String none(String html) {
        return none(html, PLACEHOLDER_BASE_URI).replace(PLACEHOLDER_BASE_URI, "");
    }

    public static String basic(String html) {
        return basic(html, PLACEHOLDER_BASE_URI).replace(PLACEHOLDER_BASE_URI, "");
    }

    public static String basicWithImages(String html) {
        return basic(html, PLACEHOLDER_BASE_URI).replace(PLACEHOLDER_BASE_URI, "");
    }

    public static String simpleText(String html) {
        return simpleText(html, PLACEHOLDER_BASE_URI).replace(PLACEHOLDER_BASE_URI, "");
    }

    public static String relaxed(String html) {
        return relaxed(html, PLACEHOLDER_BASE_URI).replace(PLACEHOLDER_BASE_URI, "");
    }


    public static String none(String html, String baseUri) {
        return Jsoup.clean(html, baseUri, Whitelist.none());
    }

    public static String basic(String html, String baseUri) {
        return clean(html, baseUri, Whitelist.basic());
    }

    public static String basicWithImages(String html, String baseUri) {
        return clean(html, baseUri, Whitelist.basicWithImages());
    }

    public static String simpleText(String html, String baseUri) {
        return clean(html, baseUri, Whitelist.simpleText());
    }
    public static String relaxed(String html, String baseUri) {
        return clean(html, baseUri, Whitelist.relaxed());
    }

    public static String clean(String html, String baseUri, Whitelist whitelist) {
        Document.OutputSettings settings = new Document.OutputSettings();
        settings.escapeMode(Entities.EscapeMode.base);
        return Jsoup.clean(html, baseUri, whitelist, settings);
    }
    public static String html(String content) {
        if(content==null) return "";
        String html = content;
        html = StringUtils.replace(html, "'", "&apos;");
        html = StringUtils.replace(html, "\"", "&quot;");
        html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
        //html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
        html = StringUtils.replace(html, "<", "&lt;");
        html = StringUtils.replace(html, ">", "&gt;");
        return html;
    }


    private HtmlCleaner() {}
}
