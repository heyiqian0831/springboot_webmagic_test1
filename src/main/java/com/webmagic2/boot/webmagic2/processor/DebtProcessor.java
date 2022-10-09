package com.webmagic2.boot.webmagic2.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @ClassName DebtProcessor
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/7 20:55
 * @Version 1.0
 */
public class DebtProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
//        System.out.println(page.getHtml().toString().length());
        //解析新的url地址

        //解析公司信息并存储
        paraseDetails(page);
    }

    /**
    *@author 何义祈安
    *@Description 解析财务报告详细信息
    **/
    private void paraseDetails(Page page) {
        Html html = page.getHtml();
        System.out.println(html);
        String test = html.$("div.subnav tip-nav a#zyzb_a").toString();
        System.out.println("_____"+html.$("div.subnav tip-nav a#zyzb_a").toString());
        page.putField("test",test);
        String s = html.css("div.qphox div.tmbox ul > li").toString();
        System.out.println("++++"+s);
        String s1 = html.$("div.qphox div.tmbox ul").toString();
        System.out.println("+++"+s1);
        String s2 = html.css("div.main div#divBody div.content").toString();
        System.out.println("------"+s2+"-------");
        page.putField("s",s);
    }
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");

    private Site site = Site.me()
            .setCharset("UTF-8") //编码
            .setSleepTime(1) //抓取时间间隔
            .setTimeOut(1000*10) //超时时间
            .setRetrySleepTime(3000) //重试时间
            .setRetryTimes(3); //重试次数
    @Override
    public Site getSite() {
        return Site.me();
    }

    private static void setGetHeaders(Request request) {
//        request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        request.addHeader("Accept-Encoding","gzip, deflate, br");
//        request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
//        request.addHeader("Cache-Control","max-age=0");
//        request.addHeader("Connection","keep-alive");
//        request.addHeader("Host","https://data.eastmoney.com");
//        request.addHeader("Referer","https://search.51job.com/");
//        request.addHeader("Upgrade-Insecure-Requests","1");
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//        request.addHeader("Cookie","_uab_collina=164697140224744622087697; guid=68000cc512692910ebca2e4b88483fc3; " +
//                "nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D" +
//                "%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; _ujz=MTM1NDI3NTI4MA%3D%3D; " +
//                "ps=needv%3D0; 51job=cuid%3D135427528%26%7C%26cusername%3DkR0zqGBUwsJ21RBVsr7IrV6QnDUpssj7I9RHuRbAOHo%253D%26%7C%26" +
//                "cpassword%3D%26%7C%26cname%3DC4s7gOjDziCFEBwn38nIIw%253D%253D%26%7C%26cemail%3D9%252BHyMY7NfA9cKB6f4EjaMu2JKkO" +
//                "vVqXybItUpoY0EpU%253D%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0gtvb.ae5XXM%26%7C%26cconfirmkey" +
//                "%3DjirwMYm783yKE%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3DjinFgXciPS3Q6%26" +
//                "%7C%26to%3D79571c03a0b0fd50d71fa00e004d4b36622aced5%26%7C%26; privacy=1648468489; slife=lastlogindate%3D20220328%26%7C" +
//                "%26; acw_tc=76b20ffb16484706993188426e08e496293046ff359eb6f0e29316b31614e1; acw_sc__v2=6241aad145405bfdaa3569f3dcc9bc1074627892; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA04%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch2%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch3%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch4%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%B2%E2%CA%D4%B9%A4%B3%CC%CA%A6%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21collapse_expansion%7E%601%7C%21; ssxmod_itna=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBIdD/G+DnqD=GFDK40EAyQD7+K0580riSnNqH87G0oeeImS7bbqKC45c1rDCPGnDBIhxHYexiiuDCeDIDWeDiDGR7D=xGYDj0F/C9Dm4i7DYqGRDB=UCqDf+qGW7uQDmLNDGLP6D7QDIw6gnR2DLeDSQ7tPWG=DjMbD/fhW2h6YapaCUktroZpxYxDHBrHbMxx1Vx5PWleuKCiDtqD94m=Db+L3cZ6lWCvIK7+YnODe+AhWQYmtID5mB9GX8Aqq0O4b8Y4bYD/XVOzdDu4CDD3PijU1xxD==; ssxmod_itna2=YqjxuDBD2ii=SqBPGKam=rT44mTH2u+qBKG9QyWDBqDKGaWs=K4o8kCsa/zMQ8Eqid39=D20+TbSwgDqLB=BpiGm9OA2GhTh4T7HIdV87FWfhVII8OXWdSUfgX0NdNz1curXv7V9q=rQC=Y1Zp5s26K7Z67Og77C4UpsQtF+e4apdK9IqFO1aF9FS6mAIgopT1HILBE+VZ+f7RACHS5jBoLLPhdXj170yUY+/KoTick1ZtcF3kFO/6nuoVIO4SMW0DP9wUbncxUO=GEBirDI75KSbXRaR6PgkXb5Ek=WcMdgQIl244wuz9bfylmycUy3i0dI5Mfw4ZuFiylRH1mAOW7x6IuG29QxDKdYD7=DYFdeD===");
    }

    public static void main(String[] args) {
        Request request = new Request("https://data.eastmoney.com/stockdata/003023.html");
        setGetHeaders(request);
        Spider.create(new DebtProcessor())
                .addRequest(request)
//                .addUrl("https://data.eastmoney.com/stockdata/003023.html")
                .thread(10)
                .run();
    }
}
