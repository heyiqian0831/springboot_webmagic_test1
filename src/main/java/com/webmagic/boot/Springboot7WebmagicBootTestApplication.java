package com.webmagic.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时注解，使用定时任务，需要先开启定时任务，可以让爬虫在固定时间内自动爬取
@EnableScheduling
public class Springboot7WebmagicBootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                Springboot7WebmagicBootTestApplication.class, args);
    }

}
