package com.webmagic.boot.service;

import com.webmagic.boot.mgb.pojo.Item;

import java.util.List;

public interface ItemService {

    /**
    *@author
     * 保存商品
     **/
    public void save(Item item);

    /**
     *@author
     * 根据条件查询商品
     **/
    public List<Item> findAll(Item item);

}
