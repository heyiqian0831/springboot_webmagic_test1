package com.webmagic.boot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @ClassName HtpGetTest
 * @Description HttpClient-Get带参请求
 * @Author 何义祈安
 * @Date 2022/9/23 11:51
 * @Version 1.0
 */
@SpringBootTest
public class HtpGetTest {

    @Test
    public void httpgettest() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet对象，设置url访问路径
        HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&pvid=632217813082480e8292198033386501&s=56&click=0&page=5");

        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求，获取返回的response
            response = httpClient.execute(httpGet);

            //解析响应
            if(response.getStatusLine().getStatusCode() == 200){  //判断是否访问成功
                String content = EntityUtils.toString(response.getEntity());
                System.out.println("content = " + content);
                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpClient.close();
        }
    }

}
