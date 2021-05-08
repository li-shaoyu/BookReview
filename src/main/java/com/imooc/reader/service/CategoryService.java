package com.imooc.reader.service;

import com.imooc.reader.entity.Category;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description 图书分类服务接口
 * @date 2021/5/8 21:33
 * @Param
 * @return
 */
public interface CategoryService {
    // list集合存储
    public List<Category> selectAll();
}