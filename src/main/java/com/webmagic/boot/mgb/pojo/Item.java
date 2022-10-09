package com.webmagic.boot.mgb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 何义祈安
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("jd_item")
public class Item implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long spu;

    private Long sku;

    private String title;

    private Long price;

    private String picture;

    private String url;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

}
