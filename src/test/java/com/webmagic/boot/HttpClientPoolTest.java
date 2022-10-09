package com.webmagic.boot;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName HttpClientPoolTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/24 11:02
 * @Version 1.0
 */
public class HttpClientPoolTest {

    @Test
    public void HttpClientPoolTest1(){
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的最大链接数
        cm.setDefaultMaxPerRoute(10);

        //使用连接池管理器发起请求
        doGet(cm);
//        doGet(cm);
    }

    private void doGet(PoolingHttpClientConnectionManager cm) {
        //不需要每次发起请求都创建请求对象，只需要从连接池中获取HttpClient对象
        //创建HttpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        //创建httpGet对象，用于设置url访问路径
        HttpGet httpGet = new HttpGet("https://blog.csdn.net/weixin_44766232/article/details/121185064");

        //配置请求参数
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000) //创建请求连接的最长时间，单位：毫秒
                .setConnectionRequestTimeout(500) //设置获取链接的最长时间，单位：毫秒
                .setSocketTimeout(10*1000) //设置数据传输的最长时间，单位：毫秒
                .build();

        //给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            //通过httpClient对象发起请求，接收返回对象
            response = httpClient.execute(httpGet);

            //如果请求成功，解析返回对象成字符串类型,这种解析方式成本高
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity());
                Document doc = Jsoup.parse(content);
                Element element = doc.select("[class=article-info-box] > div").first();
                System.out.println(element.attr("class"));
                String str = "234234";
                System.out.println(Long.valueOf(str));
                System.out.println(Long.parseLong(str));
                System.out.println(element);
//                System.out.println(element.text());
//                System.out.println(content.length());
//                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //在连接池的方法里不能关闭HttpClient，油连接池管理HttpClient
        }
    }
}

