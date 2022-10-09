package com.webmagic.boot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName JsoupGetElementByDOM
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/24 17:37
 * @Version 1.0
 */
public class JsoupGetElementByDOM {
    @Test
    public void testDOM() throws Exception {
        //解析HTML文件或者URL，获取document对象
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 2000);

        //获取元素（element就是元素）
        //1. 根据id查询元素 getElementById
//        Element element = doc.getElementById("back");

        //2.根据标签获取元素getElementByTag
//        Element element = doc.getElementsByTag("p").first();

        //3.根据class获取元素getELementByClass
//        Element element = doc.getElementsByClass("innr").first();

        //4.根据属性获取元素getElementByAttribute
//        Element element = doc.getElementsByAttribute("title").first();

        //5.根据属性和属性值获取元素getElementsByAttributeValue
        Element element = doc.getElementsByAttributeValue("class", "box_hd").first();

        //打印获取到的元素
        System.out.println("获取到的元素 = " + element);
        System.out.println("element = " + element.text());


    }
}
