package com.webmagic.boot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * @ClassName JsoupGetDateFElementTest
 * @Description 15
 * @Author 何义祈安
 * @Date 2022/9/24 20:51
 * @Version 1.0
 */
public class JsoupGetDateFElementTest {
    @Test
    public void JsoupGetDateFElement() throws Exception {
        //解析HTML文件或者RUL，获取document对象
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 20000);

        //通过对象获取元素
//        Element element = doc.getElementById("back");
//        Element element = doc.getElementsByTag("p").first();
        Element element = doc.getElementsByAttribute("title").first();

        //1.从元素中获取id
        String id = element.id();

        //2.从元素中获取className
        //单个className
        String className = element.className();
        //多个className
        Set<String> classSet = element.classNames();
        for(String s : classSet){
            System.out.println(s);
        }

        //3.从元素中获取属性的值attr
        String title = element.attr("title");

        //4.从元素中获取所有属性attributes 返回的是属性名 + 属性值
        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

        //5.从元素中获取文本内容text
        String content = element.text();
        System.out.println(content);

        System.out.println("获取到的内容 = " + title);
    }
}
