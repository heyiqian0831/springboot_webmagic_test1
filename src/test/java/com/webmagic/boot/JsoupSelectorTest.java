package com.webmagic.boot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName JsoupSelectorTest
 * @Description 16
 * @Author 何义祈安
 * @Date 2022/9/24 21:10
 * @Version 1.0
 */
public class JsoupSelectorTest {

    @Test
    public void jsoupSelector() throws Exception {
        //解析HTML文件获取URL地址，返回document对象
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 20000);

        //tagname: 通过标签查找元素，例如 span
//        Elements elements = doc.select("span");
//        for(Element element : elements){
//            System.out.println(element.text());
//        }
        //getElements的方法的效果一样可以
//        Elements elements1 = doc.getElementsByTag("span");
//        for(Element e : elements1){
//            System.out.println(e.text());
//        }

        //#id: 通过id查找元素: 例如 #back
        Element elementid = doc.select("#back").first();
        System.out.println("b"+elementid.text()+"a");

        //.class: 通过class名查找元素 例如：.innl
        Element elementclass = doc.select(".innl").first();
        System.out.println(elementclass.text());

        //[attribute]: 通过属性名称来查找于元素  例如：[title]
        Element elementattr = doc.select("[title]").first();
        System.out.println(elementattr.text());

        //[attr=value] 通过属性=属性名来查找元素，例如
        Element elementattrvalue = doc.select("[class=city_con]").first();
        System.out.println(elementattrvalue.text());
    }
}
