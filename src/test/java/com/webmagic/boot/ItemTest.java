package com.webmagic.boot;

import com.webmagic.boot.mgb.pojo.Item;
import com.webmagic.boot.service.ItemService;
import com.webmagic.boot.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @ClassName ItemTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/26 11:21
 * @Version 1.0
 */
//@Scheduled(fixedDelay = 100)
public class ItemTest {
    @Autowired
    private ItemService itemService;
    HttpUtils httpUtils = new HttpUtils();


    @Test
    public void itemTask(){
        //生命需要解析的初始地址，最后的页码数做拼接
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA" +
                "&pvid=632217813082480e8292198033386501&s=56&click=0&page=";
        int j = 1;
        System.out.println(url+j);
        //根据页面对手机的搜索结果进行便利解析，获取解析后的字符串内容
        for(int i = 1; i < 5; i = i+2){
            String html = httpUtils.doGetHtml(url+i);
            System.out.println(html);
            //进一步解析获取到的信息，获取商品数据并储存
            this.parse(html);
        }
    }

    //解析页面，获取商品数据并存储
    private void parse(String html) {
        //解析html获取document
        Document doc = Jsoup.parse(html);
        System.out.println(html);
        //获取spu，这里是获取一整个元素
        Elements spuEles = doc.select("div#J_goodsList > ul > li");

        for(Element spuEle : spuEles){
            //获取spu
            String data_spu = spuEle.attr("data-spu");
            Long spu = Long.valueOf(data_spu);
            //Long spu = Long.parseLong(spuEle.attr("data-spu"));

            //获取sku信息
            Elements skuEles = spuEle.select("li.ps-item");

            for(Element skuEle : skuEles){
                Long sku = Long.parseLong(
                        skuEle.select("[data-sku]").attr("data-sku"));

                //根据sku查询商品数据
                Item item = new Item();
                item.setSku(sku);
                List<Item> list = itemService.findAll(item);

                //如果商品存在，就新进下一个循环，该商品不保存
                if(list.size()>0){
                    continue;
                }
                //设置商品的spu
                item.setSpu(spu);

                //设置商品的详情url
                String itemUrl = "https://item.jd.com/" + sku + ".html";
                item.setUrl(itemUrl);

                //获取商品的图片
                String picUrl = "https:"+skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                picUrl = picUrl.replace("/n9/","/n0/");
                String picName = httpUtils.doGetImage(picUrl);
                item.setPicture(picName);

                //获取商品的价格(需要一个新的将json转成Long的工具类)
//                String priceJson = httpUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
//                Long price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
//                item.setPrice(price);

                //获取商品的标题
                String itemInfo = httpUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                item.setTitle(title);

                //保存数据
                itemService.save(item);
            }
        }


    }

}
