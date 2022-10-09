package com.webmagic2.boot.webmagic2.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webmagic2.boot.webmagic2.Service.JobInfoService;
import com.webmagic2.boot.webmagic2.mgb.mapper.JobInfoMapper;
import com.webmagic2.boot.webmagic2.mgb.pojo.JobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName JobInfoServiceImpl
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/10/3 16:58
 * @Version 1.0
 */
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(JobInfo jobInfo) {
        try {
            //先从数据库查询数据，根据 发布日期 和 URL 查询
            JobInfo jobInfo1 = new JobInfo();
            jobInfo1.setUrl(jobInfo.getUrl());
            jobInfo1.setTime(jobInfo.getTime());
            QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("url", jobInfo1.getUrl());
            wrapper.eq("time", jobInfo1.getTime());
            List<JobInfo> list = jobInfoMapper.selectList(wrapper);

            if (list.size() == 0) {
                //没有查询到数据则新增或者修改数据
                jobInfoMapper.insert(jobInfo);
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {

        QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("url",jobInfo.getUrl());
        wrapper.eq("time",jobInfo.getTime());
        List<JobInfo> list = jobInfoMapper.selectList(wrapper);
        return list;
    }
}
