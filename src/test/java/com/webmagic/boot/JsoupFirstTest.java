package com.webmagic.boot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName JsoupFirstTest
 * @Description
 * @Author 何义祈安
 * @Date 2022/9/24 16:27
 * @Version 1.0
 */
public class JsoupFirstTest {

    @Test
    public void jsoupFirstTest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content.length());

        //jsoup可以解析某个urll地址，html文本内容，可通过dom,css以及类似于jQuary的操作方式来取出和操作数据

        //解析url地址，第一个参数是要访问的url，第二个是访问的超市时间
        Document doc = Jsoup.parse(
                new URL("http://www.itcast.cn"),2000);

        //使用标签选择器，获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();

        //打印结果
        System.out.println("title = " + title);
    }
}
