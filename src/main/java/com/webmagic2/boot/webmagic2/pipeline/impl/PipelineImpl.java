package com.webmagic2.boot.webmagic2.pipeline.impl;

import com.webmagic2.boot.webmagic2.mgb.mapper.JobInfoMapper;
import com.webmagic2.boot.webmagic2.mgb.pojo.JobInfo;
import com.webmagic2.boot.webmagic2.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * @ClassName PipelineImpl
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/4 15:20
 * @Version 1.0
 */
@Component
public class PipelineImpl implements Pipeline {

    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        JobInfo jobInfo = resultItems.get("jobInfo");

//        System.out.println(jobInfo.getCompanyName());
//        System.out.println(jobInfo.getTime());

        if(jobInfo != null){
            //如果有值则进行保存
            int insert = jobInfoMapper.insert(jobInfo);
            System.out.println(insert);
        }
    }
}
