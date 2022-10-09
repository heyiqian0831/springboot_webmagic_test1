package com.webmagic.boot;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HttpPostParamTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/24 10:30
 * @Version 1.0
 */
public class HttpPostParamTest {

    @Test
    public void HttpPostParamTest() throws IOException {
        //创建HttpClient对象，用于发送请求
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建httpPost对象，设置url访问路径
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");

        //声明list集合，封装表单中的参数（Post请求一般用表单提交方式发起请求）
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //设置的请求地址是：http://yun.itheima.com/search?keys=Java
        params.add(new BasicNameValuePair("keys","Java"));

        //创建表单的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");

        //将表单的Entity对象设置post请求对象的url路径后面
        httpPost.setEntity(formEntity);
        System.out.println("发起请求的httpPost："+httpPost);

        //后面要用到的对象写在tryCatch外面
        CloseableHttpResponse response = null;
        try {
            //用httpClients发起请求
            response = httpClient.execute(httpPost);

            //解析资源
//            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
                System.out.println(content.length());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源

            //关闭response
            response.close();
            //关闭httpClient
            httpClient.close();
        }
    }
}
