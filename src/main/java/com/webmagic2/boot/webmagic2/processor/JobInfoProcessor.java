package com.webmagic2.boot.webmagic2.processor;

import com.webmagic2.boot.webmagic2.mgb.pojo.JobInfo;
import com.webmagic2.boot.webmagic2.pipeline.Pipeline;
import com.webmagic2.boot.webmagic2.pipeline.impl.PipelineImpl;
import org.jsoup.Jsoup;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @ClassName JobInfoProcessor
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/3 17:20
 * @Version 1.0
 */
@Component
public class JobInfoProcessor implements PageProcessor{

    @Autowired
    private Pipeline pipeline;


    /**
        *解析初始页面，获取指定页面
    **/
    @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        List<Selectable> nodes =
                page.getHtml().$("div#resultList div.el").nodes();

        //判断获取到的集合是否为空
        if(nodes.isEmpty()){
            try {
                //如果为空，表示这是招聘信息的详情页，解析页面，获取招聘详情信息，保存数据
                saveJobInfo(page);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            //如果不为空，表示这是列表页面，将列表页面中的详情页url加入到任务栏中
            for (Selectable node : nodes) {
                //获取招聘信息详情页url
                String jobUrl = node.links().toString();
                //添加到url任务列表中，等待下载
//                page.addTargetRequest(jobUrl);

                //获取翻页按钮的超链接
//                List<String> listUrl =
//                        page.getHtml().css("div.p_in li.bk").links().all();
                //添加到任务列表中
//                page.addTargetRequests(listUrl);
            }
        }
    }

    /**
        *页面解析获取数据
    **/
    private void saveJobInfo(Page page) {
        //创建招聘信息对象
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();

        //获取公司名称
        jobInfo.setCompanyName(html.$("div.tHeader p.cname a").toString());
        jobInfo.setTime("sdfsdf");
        //公司地址
        jobInfo.setCompanyAddr(html.$("div.tBorderTop_box:nth-child(3) p.fp", "text").toString());
//        公司信息
        jobInfo.setCompanyInfo(html.$("div.tmsg", "text").toString());
//        职位名称
        jobInfo.setJobName(html.$("div.tHeader > div.in > div.cn > h1", "text").toString());
//        工作地点
        jobInfo.setJobAddr(html.$("div.tHeader > div.in > div.cn > span.lname", "text").toString());
//        职位信息
        jobInfo.setJobInfo(Jsoup.parse(html.$("div.tBorderTop_box:nth-child(2)").toString()).text());
//        工资范围
        String salaryStr = html.$("div.tHeader > div.in > div.cn > strong", "text").toString();
//        jobInfo.setSalaryMin(MathSalary.getSalary(salaryStr)[0]);
//        jobInfo.setSalaryMax(MathSalary.getSalary(salaryStr)[1]);
//        职位详情url
        jobInfo.setUrl(page.getUrl().toString());
//        职位发布时间
        String time = html.$("div.jtag > div.t1 > span.sp4", "text").regex(".*发布").toString();
        jobInfo.setTime(time.substring(0, time.length() - 2));

//保存数据
        page.putField("jobInfo", jobInfo);
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

    //通过定时任务的注解来启动这个方法，initialDelay：隔多久启动，fixedDelay：每间隔多长时间
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public static void main(String[] args) {
        PipelineImpl pipeline = new PipelineImpl();
        //访问入口：
        Spider.create(new JobInfoProcessor())
                .addUrl("https://search.51job.com/list/000000,000000,0000,01%252c32,9,99,java,2,1.html")
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(
                                new BloomFilterDuplicateRemover(100000000)))
                .addPipeline(pipeline)
                .thread(5)
                .run();
    }

}
