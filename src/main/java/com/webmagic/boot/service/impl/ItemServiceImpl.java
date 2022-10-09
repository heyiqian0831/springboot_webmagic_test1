package com.webmagic.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webmagic.boot.mgb.mapper.ItemMapper;
import com.webmagic.boot.mgb.pojo.Item;
import com.webmagic.boot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ItemServiceImpl
 * @Description TODO
 * @Author 何义祈安
 * @Date 2022/9/25 11:51
 * @Version 1.0
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void save(Item item) {
        int insert = itemMapper.insert(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        List<Item> list = itemMapper.selectList(wrapper);
        return list;
    }
}
