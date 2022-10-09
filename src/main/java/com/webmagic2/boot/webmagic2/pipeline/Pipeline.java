package com.webmagic2.boot.webmagic2.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

public interface Pipeline extends us.codecraft.webmagic.pipeline.Pipeline {
    // ResultItems保存了抽取结果，它是一个Map结构，
    // 在page.putField(key,value)中保存的数据，
    //可以通过ResultItems.get(key)获取
    public void process(ResultItems resultItems, Task task);
}
