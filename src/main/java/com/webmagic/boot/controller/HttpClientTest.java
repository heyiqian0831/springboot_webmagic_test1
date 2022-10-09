package com.webmagic.boot.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @ClassName HttpClientTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/23 11:20
 * @Version 1.0
 */
@Controller
public class HttpClientTest {

    public void Firstdemo() throws IOException {
        System.out.println( "Hello World!" );
        //1.打开浏览器，创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet("https://blog.csdn.net/weixin_44766232/article/details/126725400?spm=1001.2014.3001.5502");
        //3.按回车，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.解析响应，
        //判断状态码是否是200
        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity,"utf8");

            System.out.println(content);
        }

    }
}
