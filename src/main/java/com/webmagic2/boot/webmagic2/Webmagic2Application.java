package com.webmagic2.boot.webmagic2;

import com.webmagic2.boot.webmagic2.processor.JobInfoProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling //开启定时任务
public class Webmagic2Application {

    public static void main(String[] args) {
        SpringApplication.run(Webmagic2Application.class, args);
    }

}
