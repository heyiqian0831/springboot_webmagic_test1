package com.webmagic.boot;

import com.webmagic.boot.controller.HttpClientTest;
import com.webmagic.boot.controller.HttpGetTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Springboot7WebmagicBootTestApplicationTests {

    @Autowired
    private HttpClientTest httpClientTest;
    @Autowired
    private HttpGetTest httpGetTest;

    //测试HttpClientTest类
    @Test
    void HttpClientTest() throws IOException {
        httpClientTest.Firstdemo();
    }

    //测试HttpGet类
    @Test
    void HttpGetTest() throws IOException {
        httpGetTest.httpgettest();
    }


}
