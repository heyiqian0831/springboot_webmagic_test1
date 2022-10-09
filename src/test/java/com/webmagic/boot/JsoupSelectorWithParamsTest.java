package com.webmagic.boot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName JsoupSelectorWithParamsTest
 * @Description 17
 * @Author 何义祈安
 * @Date 2022/9/24 21:41
 * @Version 1.0
 */
public class JsoupSelectorWithParamsTest {

    @Test
    public void jsoupSelectorWithParams() throws IOException {
        //解析HTML文件或者URL路径，返回document对象
        Document doc = Jsoup.parse(new URL("https://emweb.securities.eastmoney.com/PC_HSF10/NewFinanceAnalysis/Index?type=web&code=sz003023"), 20000);

        System.out.println(doc);
        //这下面的元素也就是标签，元素是从一个标签的开头的到结尾整个标签的内容
        //el#id: 元素+id的值 例如：h3#city_bj
//        Element elementElId = doc.select("h3#city_bj").first();
//        System.out.println(elementElId.text());

        //el.class: 元素+class的值 例如：li.class
//        Element elementelclass = doc.select("div.innl").first();
//        System.out.println(elementelclass.text());
//
//        //el[attr]: 元素+属性名 例如： span[abc]
//        Element elementelattr = doc.select("div[class]").first();
//        System.out.println(elementelattr.text());
//
//        //任意组合查找：比如：span[abc].s_name
//        Element elementzuhe = doc.select("div[class].innl").first();
//        System.out.println(elementzuhe.text());
//
//        //ancestor child: 查找某个元素下子元素，例如：.city_con li :查找class=city_con下的所有li
//        Elements ancestorchild = doc.select("div.city_con[style] li");
//        for(Element ee : ancestorchild){
//            System.out.println(ee.text());
//        }
//
//        //parent > child：查找某个父元素下的直接子元素 例如：.city_con > ul > li
//        //查找city_con第一级（直接子元素）的ul,再找ul的第一个li
//        Element select = doc.select("ul > li").first();
//        System.out.println(select.text());
//
//        //parent > *: 查找某个父元素的所有直接子元素
//        Elements elements = doc.select("ul > *");
//        System.out.println(elements.text());

    }
}
