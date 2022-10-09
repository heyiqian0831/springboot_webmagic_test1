package com.webmagic2.boot.webmagic2.mgb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 何义祈安
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "job_info")
public class JobInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String companyName;

    private String companyAddr;

    private String companyInfo;

    private String jobName;

    private String jobAddr;

    private String jobInfo;

    private Float salaryMin;

    private Float salaryMax;

    private String url;

    private String time;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyAddr=").append(companyAddr);
        sb.append(", companyInfo=").append(companyInfo);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobAddr=").append(jobAddr);
        sb.append(", jobInfo=").append(jobInfo);
        sb.append(", salaryMin=").append(salaryMin);
        sb.append(", salaryMax=").append(salaryMax);
        sb.append(", url=").append(url);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
