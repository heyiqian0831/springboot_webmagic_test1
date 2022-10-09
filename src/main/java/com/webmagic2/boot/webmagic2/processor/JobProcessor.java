package com.webmagic2.boot.webmagic2.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;

/**
 * @ClassName JobProcessor
 * @Description 创建Processor类，继承PageProcessor，然后在Spider中创建爬虫
 * @Author 何义祈安
 * @Date 2022/10/1 19:31
 * @Version 1.0
 */
@Component
public class JobProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //page.getHtml() 返回的是一个Html对象，抽取方法它实现了Selectable接口
//        page.putField("author",page.getHtml().css("div#J_footer p.mod_service_txt").all());
        System.out.println(page.getHtml());  //打印HTML格式的网页
//        System.out.println(page);不知道是什么，一堆数字
        //下面两条测试一下toString()的结果，结果都一样都是查到的整条元素
//        System.out.println("____________");
//        System.out.println(page.getHtml().css("div#J_footer p.mod_service_txt"));
//        System.out.println("______");
//        System.out.println(page.getHtml().css("div#J_footer p.mod_service_txt").toString());
//        System.out.println("____________");
//        List<String> list = page.getHtml().css("div#J_footer li").regex(".*精致.*").all();
//        System.out.println(list);
//        System.out.println("____________");
//        System.out.println(page.getHtml().links().get());
//        System.out.println("___//get() 和 toString()两个方法都是返回第一个字符串");
//        System.out.println(page.getHtml().links().toString());
//        System.out.println("____________all()方法是返回全部找到的元素,从结果看来是返回一个list");
//        System.out.println(page.getHtml().links().all());
//        System.out.println("____________获取链接并加入到待抓取的队列中去，当前url应该是不允许爬虫链接估计是");
//        System.out.println(page.getHtml().links().regex("https://pro.jd.com/mall/active/3bVDLXHdwVmdQksGF8TtS7ocq1NY.*").all());
//        page.addTargetRequests(page.getHtml().links().regex("https://pro.jd.com/mall/active/3bVDLXHdwVmdQksGF8TtS7ocq1NY.*").all());
//        page.addTargetRequests(page.getHtml().links().regex("https://pro.jd.com/mall/active/3bVDLXHdwVmdQksGF8TtS7ocq1NY.*").all());
//        page.addTargetRequests(page.getHtml().links().regex("https://pro.jd.com/mall/active/3bVDLXHdwVmdQksGF8TtS7ocq1NY.*").all());
////        System.out.println(page.getHtml().css("div"));
//        System.out.println(page.getHtml());

    }

    //Site.me()可以对爬虫进行一些配置，包括编码、抓取间隔、超时时间、重试次数等
    private Site site = Site.me()
            .setCharset("UTF-8") //编码
            .setSleepTime(1) //抓取时间间隔
            .setTimeOut(1000*10) //超时时间
            .setRetrySleepTime(3000) //重试时间
            .setRetryTimes(3); //重试次数
    @Override
    public Site getSite() {
        return site;
    }
//    https://emweb.securities.eastmoney.com/PC_HSF10/FinanceAnalysis/Index?type=web&code=SZ003023#bfbbb-0
//    https://www.jd.com/news.html?id=37090
    public static void main(String[] args) {
        //Spider是Webmagic爬虫启动的入口，需要用一个PageProcessor创建一个Spider对象，然后用run()启动
        Spider.create(new JobProcessor())
                .addUrl("https://emweb.securities.eastmoney.com/PC_HSF10/FinanceAnalysis/Index?type=web&code=SZ003023#bfbbb-0") //添加初始的URL
                .addPipeline(new FilePipeline("C:\\Users\\BYinH\\Desktop\\ne")) //添加一个Pipeline，一个Spider可以有多个Pipeline
                .thread(5) //设置线程数
                .setScheduler(new QueueScheduler()
                                .setDuplicateRemover(
                                new BloomFilterDuplicateRemover(1000000000))) //参数是要对多少条数据进行去重
                .run();
    }
}
