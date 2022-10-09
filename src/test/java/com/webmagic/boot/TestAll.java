package com.webmagic.boot;

import com.webmagic.boot.mgb.pojo.Item;
import com.webmagic.boot.utils.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


/**
 * @ClassName TestAll
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/26 13:09
 * @Version 1.0
 */
public class TestAll {
//    @Autowired
//    private HttpUtils httpUtils;

    @Test
    public void TestAll() throws IOException {
        HttpUtils httpUtils = new HttpUtils();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&pvid=632217813082480e8292198033386501&s=56&click=0&page=5");
//        String html = httpUtils.doGetHtml("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&pvid=632217813082480e8292198033386501&s=56&click=0&page=5");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
        Document doc = Jsoup.parse(content);
        System.out.println("__________________________________________");
        Elements spuEles = doc.select("div#J_goodsList > ul > li");
        System.out.println(spuEles);
        for (Element spuEle : spuEles) {
            //获取spu
            String data_spu = spuEle.attr("data-spu");
            Long spu = Long.valueOf(data_spu);
            System.out.println(spu);
            //Long spu = Long.parseLong(spuEle.attr("data-spu"));

            //获取sku信息
            Elements skuEles = spuEle.select("li.ps-item");

            for (Element skuEle : skuEles) {
                Long sku = Long.parseLong(
                        skuEle.select("[data-sku]").attr("data-sku"));

                //根据sku查询商品数据
                Item item = new Item();
                item.setSku(sku);
//                List<Item> list = itemService.findAll(item);

                //如果商品存在，就新进下一个循环，该商品不保存
//                if(list.size()>0){
//                    continue;
//                }
                //设置商品的spu
                item.setSpu(spu);

                //设置商品的详情url
                String itemUrl = "https://item.jd.com/" + sku + ".html";
                item.setUrl(itemUrl);

                //获取商品的图片
                String picUrl = skuEle.select("img[data-sku]").first().attr("src");
            }
        }


    }
}
