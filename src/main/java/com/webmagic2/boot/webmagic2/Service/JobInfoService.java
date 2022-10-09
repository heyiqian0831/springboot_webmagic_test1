package com.webmagic2.boot.webmagic2.Service;

import com.webmagic2.boot.webmagic2.mgb.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {
    /**
     * 保存数据
     *
     * @param jobInfo
     */
    public void save(JobInfo jobInfo);

    /**
     * 根据条件查询数据
     *
     * @param jobInfo
     * @return
     */
    public List<JobInfo> findJobInfo(JobInfo jobInfo);
}

