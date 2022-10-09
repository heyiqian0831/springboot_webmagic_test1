package com.webmagic2.boot.webmagic2.processor;

import com.webmagic2.boot.webmagic2.mgb.pojo.JobInfo;
import com.webmagic2.boot.webmagic2.pipeline.impl.PipelineImpl;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName JobPageProcessor
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/5 11:16
 * @Version 1.0
 */
public class JobPageProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml());
        //获取请求中的上下文，该值可以传递一些数据，是key，value形式
        Map<String,Object> extras = page.getRequest().getExtras();
//        System.out.println(extras);
        String level = "list";
//        if(" ".equals(extras.get("level"))){
//            //如果有这个key,判断值是什么
//                level = "list";
//        }
//        System.out.println(level);
        //如果level为空，一般就是初始的列表页，所以要给他赋值
        switch (level){
            //如果是list说明是招聘列表页
            case "list":
                paraseList(page);
                break;
            //如果是detail,说明是招聘详情页
            case "detail":
                saveDetail(page);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + level);
        }
    }

    /**
     * 解析列表页
     *
     * @param page
     */
    private void paraseList(Page page) {
        //判断网页返回的是不是json格式的数据，如果是，可以用获取json的方式获取所有要获取的数据
        Json json = page.getJson();
        // 获取所有的目标详情url
        List<String> urlList = json.jsonPath("engine_jds[*].job_href").all();
        for (String s : urlList) {
            Request request = new Request(s);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("level","detail"); // 标识位
            request.setExtras(map);
            setGetHeaders(request);
            page.addTargetRequest(request);
        }


    }

    private void setGetHeaders(Request request) {
        request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        request.addHeader("Accept-Encoding","gzip, deflate, br");
        request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
        request.addHeader("Cache-Control","max-age=0");
        request.addHeader("Connection","keep-alive");
        request.addHeader("Host","jobs.51job.com");
        request.addHeader("Referer","https://search.51job.com/");
        request.addHeader("Upgrade-Insecure-Requests","1");
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        request.addHeader("Cookie","_uab_collina=164697140224744622087697; guid=68000cc512692910ebca2e4b88483fc3; " +
                "nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D" +
                "%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; _ujz=MTM1NDI3NTI4MA%3D%3D; " +
                "ps=needv%3D0; 51job=cuid%3D135427528%26%7C%26cusername%3DkR0zqGBUwsJ21RBVsr7IrV6QnDUpssj7I9RHuRbAOHo%253D%26%7C%26" +
                "cpassword%3D%26%7C%26cname%3DC4s7gOjDziCFEBwn38nIIw%253D%253D%26%7C%26cemail%3D9%252BHyMY7NfA9cKB6f4EjaMu2JKkO" +
                "vVqXybItUpoY0EpU%253D%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0gtvb.ae5XXM%26%7C%26cconfirmkey" +
                "%3DjirwMYm783yKE%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3DjinFgXciPS3Q6%26" +
                "%7C%26to%3D79571c03a0b0fd50d71fa00e004d4b36622aced5%26%7C%26; privacy=1648468489; slife=lastlogindate%3D20220328%26%7C" +
                "%26; acw_tc=76b20ffb16484706993188426e08e496293046ff359eb6f0e29316b31614e1; acw_sc__v2=6241aad145405bfdaa3569f3dcc9bc1074627892; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA04%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch2%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch3%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch4%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%B2%E2%CA%D4%B9%A4%B3%CC%CA%A6%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21collapse_expansion%7E%601%7C%21; ssxmod_itna=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBIdD/G+DnqD=GFDK40EAyQD7+K0580riSnNqH87G0oeeImS7bbqKC45c1rDCPGnDBIhxHYexiiuDCeDIDWeDiDGR7D=xGYDj0F/C9Dm4i7DYqGRDB=UCqDf+qGW7uQDmLNDGLP6D7QDIw6gnR2DLeDSQ7tPWG=DjMbD/fhW2h6YapaCUktroZpxYxDHBrHbMxx1Vx5PWleuKCiDtqD94m=Db+L3cZ6lWCvIK7+YnODe+AhWQYmtID5mB9GX8Aqq0O4b8Y4bYD/XVOzdDu4CDD3PijU1xxD==; ssxmod_itna2=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBKG9QyWDBqDKGaWs=K4o8kCsa/zMQ8Eqid39=D20+TbSwgDqLB=BpiGm9OA2GhTh4T7HIdV87FWfhVII8OXWdSUfgX0NdNz1curXv7V9q=rQC=Y1Zp5s26K7Z67Og77C4UpsQtF+e4apdK9IqFO1aF9FS6mAIgopT1HILBE+VZ+f7RACHS5jBoLLPhdXj170yUY+/KoTick1ZtcF3kFO/6nuoVIO4SMW0DP9wUbncxUO=GEBirDI75KSbXRaR6PgkXb5Ek=WcMdgQIl244wuz9bfylmycUy3i0dI5Mfw4ZuFiylRH1mAOW7x6IuG29QxDKdYD7=DYFdeD===");
    }

    /**
     * 解析详情页
     *
     * @param page
     */
    private void saveDetail(Page page) {
        Html html = page.getHtml();
        String companyName = html.$("p.cname > a:first-child").xpath("///allText()").get();
        String companyAddr = html.$("div.tCompany_main > div:nth-of-type(2) p").xpath("///allText()").get();
        String companyInfo = html.$("div.tCompany_main > div:last-child").xpath("///allText()").get();
        String jobName = html.$("div.cn > h1").xpath("///allText()").get();
        String tempJobStr = html.$("p.msg.ltype").xpath("///allText()").get();
        String jobAddr = "";
        String time = "";
        if (StringUtils.isNotBlank(tempJobStr)){
            String[] split = tempJobStr.split("|");
            if (split.length > 4){
                jobAddr = split[0];
                time = split[split.length-1];
            }
        }
        String jobInfoStr = html.$("div.bmsg.job_msg.inbox p").xpath("///allText()").get();
        String tempSalaryStr = html.$("div.cn strong").xpath("///allText()").get();
        String salaryMin = "";
        String salaryMax = "";
        if (StringUtils.isNotBlank(tempSalaryStr)){
            String[] salarySplit = tempSalaryStr.split("万")[0].split("-");
            if (salarySplit.length > 1){
                salaryMin = salarySplit[0];
                salaryMax = salarySplit[1];
            }
        }
        String url = page.getUrl().get();
        // 去保存
        JobInfo jobInfo = new JobInfo();
        jobInfo.setCompanyName(companyName);
        jobInfo.setCompanyAddr(companyAddr);
        jobInfo.setCompanyInfo(companyInfo);
        jobInfo.setJobName(jobName);
        jobInfo.setJobAddr(jobAddr);
        jobInfo.setJobInfo(jobInfoStr);
        jobInfo.setUrl(url);
        jobInfo.setTime(time);
        jobInfo.setSalaryMin(Float.valueOf(salaryMin));
        jobInfo.setSalaryMax(Float.valueOf(salaryMax));

        // 交给pipeline来处理
        page.putField("jobInfo",jobInfo);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    //测试用，方便测试新的爬取语句
    public static void main(String[] args) {
        PipelineImpl pipeline = new PipelineImpl();
//    .addUrl("https://search.51job.com/list/000000,000000,0000,00,9,99,%25E4%25BF%25A1%25E6%2581%25AF%25E7%25AE%25A1%25E7%2590%2586%25E4%25B8%258E%25E4%25BF%25A1%25E6%2581%25AF%25E7%25B3%25BB%25E7%25BB%259F,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=04&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=")

        Request request = new Request("https://search.51job.com/list/000000,000000,0000,00,9,99,%25E4%25BF%25A1%25E6%2581%25AF%25E7%25AE%25A1%25E7%2590%2586%25E4%25B8%258E%25E4%25BF%25A1%25E6%2581%25AF%25E7%25B3%25BB%25E7%25BB%259F,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=04&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=");
//        request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        request.addHeader("Accept-Encoding","gzip, deflate, br");
//        request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
//        request.addHeader("Cache-Control","max-age=0");
//        request.addHeader("Connection","keep-alive");
//        request.addHeader("Host","jobs.51job.com");
//        request.addHeader("Referer","https://search.51job.com/");
//        request.addHeader("Upgrade-Insecure-Requests","1");
//        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//        request.addHeader("Cookie","_uab_collina=164697140224744622087697; guid=68000cc512692910ebca2e4b88483fc3; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; _ujz=MTM1NDI3NTI4MA%3D%3D; ps=needv%3D0; 51job=cuid%3D135427528%26%7C%26cusername%3DkR0zqGBUwsJ21RBVsr7IrV6QnDUpssj7I9RHuRbAOHo%253D%26%7C%26cpassword%3D%26%7C%26cname%3DC4s7gOjDziCFEBwn38nIIw%253D%253D%26%7C%26cemail%3D9%252BHyMY7NfA9cKB6f4EjaMu2JKkOvVqXybItUpoY0EpU%253D%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0gtvb.ae5XXM%26%7C%26cconfirmkey%3DjirwMYm783yKE%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3DjinFgXciPS3Q6%26%7C%26to%3D79571c03a0b0fd50d71fa00e004d4b36622aced5%26%7C%26; privacy=1648468489; slife=lastlogindate%3D20220328%26%7C%26; acw_tc=76b20ffb16484706993188426e08e496293046ff359eb6f0e29316b31614e1; acw_sc__v2=6241aad145405bfdaa3569f3dcc9bc1074627892; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA04%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch2%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch3%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch4%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%B2%E2%CA%D4%B9%A4%B3%CC%CA%A6%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21collapse_expansion%7E%601%7C%21; ssxmod_itna=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBIdD/G+DnqD=GFDK40EAyQD7+K0580riSnNqH87G0oeeImS7bbqKC45c1rDCPGnDBIhxHYexiiuDCeDIDWeDiDGR7D=xGYDj0F/C9Dm4i7DYqGRDB=UCqDf+qGW7uQDmLNDGLP6D7QDIw6gnR2DLeDSQ7tPWG=DjMbD/fhW2h6YapaCUktroZpxYxDHBrHbMxx1Vx5PWleuKCiDtqD94m=Db+L3cZ6lWCvIK7+YnODe+AhWQYmtID5mB9GX8Aqq0O4b8Y4bYD/XVOzdDu4CDD3PijU1xxD==; ssxmod_itna2=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBKG9QyWDBqDKGaWs=K4o8kCsa/zMQ8Eqid39=D20+TbSwgDqLB=BpiGm9OA2GhTh4T7HIdV87FWfhVII8OXWdSUfgX0NdNz1curXv7V9q=rQC=Y1Zp5s26K7Z67Og77C4UpsQtF+e4apdK9IqFO1aF9FS6mAIgopT1HILBE+VZ+f7RACHS5jBoLLPhdXj170yUY+/KoTick1ZtcF3kFO/6nuoVIO4SMW0DP9wUbncxUO=GEBirDI75KSbXRaR6PgkXb5Ek=WcMdgQIl244wuz9bfylmycUy3i0dI5Mfw4ZuFiylRH1mAOW7x6IuG29QxDKdYD7=DYFdeD===");


        //访问入口：
        Spider.create(new JobPageProcessor())
                .addRequest(request)
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(
                                new BloomFilterDuplicateRemover(100000000)))
                .addPipeline(pipeline)
                .thread(5)
                .run();
    }

}
