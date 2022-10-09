package com.webmagic.boot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName JsoupParseStringTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/24 17:09
 * @Version 1.0
 */
public class JsoupParseStringTest {

    @Test
    public void jsoupParseString() throws Exception {
        //解析url地址
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 2000);

        String title = doc.getElementsByTag("title").first().text();

        System.out.println(title);

    }
}
