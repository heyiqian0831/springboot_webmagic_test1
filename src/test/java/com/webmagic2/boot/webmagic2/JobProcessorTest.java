package com.webmagic2.boot.webmagic2;

import com.webmagic2.boot.webmagic2.processor.JobProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

/**
 * @ClassName JobProcessorTest
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/2 16:47
 * @Version 1.0
 */
@SpringBootTest
public class JobProcessorTest {

    @Autowired
    private JobProcessor jobProcessor;
    @Test
    public void testJobProcessor(){
        Spider.create(jobProcessor)
                .addUrl("https://www.jd.com/news.html?id=37090")
                .run();

    }
}
