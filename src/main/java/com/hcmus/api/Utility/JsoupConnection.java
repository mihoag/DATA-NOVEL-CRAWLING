package com.hcmus.api.Utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupConnection {
    private static final int DEFAULT_TIMEOUT = 10 * 1000; // 10 seconds
    public static Document connect(String url) throws IOException {
        return Jsoup.connect(url).timeout(DEFAULT_TIMEOUT).get();
    }
}
