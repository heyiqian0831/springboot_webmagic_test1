package com.webmagic.boot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName HttppostTest
 * @Description HttpPost请求
 * @Author 何义祈安
 * @Date 2022/9/24 10:06
 * @Version 1.0
 */
public class HttppostTest {

    @Test
    public void HttpPostTest() throws IOException {
        // 创建HttpClient对象，用于发送请求
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpPost对象
        HttpPost httpPost = new HttpPost("https://www.baidu.com/s?wd=%E4%BD%95%E4%B9%89%E7%AB%8F");
        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求，参数是http对象，返回response
            response = httpClient.execute(httpPost);

            //解析返回的响应response
            if(response.getStatusLine().getStatusCode() == 200){
                //使用entityUtils工具类将response解析成字符串
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content.length());
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放和关闭资源

            try {
                //关闭response
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpClient.close();
        }
    }
}
