package com.webmagic.boot.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName HttpUtils
 * @Description 将爬取数据的HttpClient封装成工具类，减少代码冗余
 * @Author 何义祈安
 * @Date 2022/9/25 11:59
 * @Version 1.0
 */

public class HttpUtils {
    /**
        * 创建连接池管理器
    **/
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(200);

        //设置每个主机的并发数
        cm.setDefaultMaxPerRoute(20);
    }

    /**
    *   根据请求地址下载页面数据
    **/
    public String doGetHtml(String url){
        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet请求对象，设置url地址到请求对象里面
        HttpGet httpGet = new HttpGet(url);

        //设置请求信息
        httpGet.setConfig(this.getConfig());
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            //通过httpClient发起请求，获取响应
            response = httpClient.execute(httpGet);

            //解析响应，返回结果
            if(response.getStatusLine().getStatusCode() == 200){
                //判断response的响应体是否不为空，如果不为空就使用EntityUtils解析
                if(!Objects.isNull(response.getEntity())){
                    String content = EntityUtils.toString(response.getEntity(),"utf8");
                    return content;
                }else{
                    return "响应体为空";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //释放response资源
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //httpClient对象不释放，有连接池管理
        }
        return "获取并解析失败";
    }

    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(20000) //创建链接的最长时间
                .setConnectionRequestTimeout(1000) //获取链接最长时间
                .setSocketTimeout(10000) //数据传输的最长时间
                .build();
        return config;
    }

    /**
    * 下载图片,下载图片的url就直接是图片的url
    **/
    public String doGetImage(String url){
        //获取httpClient对象，用于发送请求
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet对象，设置访问路径
        HttpGet httpGet = new HttpGet(url);

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;

        try {
            //httpClient发起请求，获取响应
            response = httpClient.execute(httpGet);
            if(response.getEntity() != null){
                //获取图片的后缀
                String extName = url.substring(url.lastIndexOf("."));

                //创建图片名,重命名图片
                String picName = UUID.randomUUID().toString() + extName;

                //下载图片
                FileOutputStream outputStream =
                        new FileOutputStream(new File("G:\\NetClassAll\\SpringBootProject\\images" + picName));
                response.getEntity().writeTo(outputStream);

                //返回图片名称
                return picName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //释放response资源
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //httpClient对象不释放，有连接池管理
        }
        return "获取并解析失败";
    }
}
