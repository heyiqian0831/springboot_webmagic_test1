package com.webmagic.boot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * @ClassName HttpGetParamTest
 * @Description 测试HttpClient
 * @Author 何义祈安
 * @Date 2022/9/23 11:50
 * @Version 1.0
 */
@SpringBootTest
public class HttpGetParamTest {
    @Test
    public void HttpGetParamTest() throws URISyntaxException, IOException {
        //1. 创建HttpClient对象
        CloseableHttpClient httpClients = HttpClients.createDefault();
        //2. 创建URIBuilder, 设置请求地址
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //3. 设置要带的参数
        uriBuilder.setParameter("key","Java");
        //4. 创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        System.out.println("发起请求的url"+httpGet);

        CloseableHttpResponse response = null;
        try {
            //5. 使用httpClient对象向浏览器服务器发起请求，获取response
            response = httpClients.execute(httpGet);
            //6. 解析response

            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
            httpClients.close();
        }
        //

        //

    }


}
