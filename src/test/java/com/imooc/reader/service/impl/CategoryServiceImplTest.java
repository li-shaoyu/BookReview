package com.imooc.reader.service.impl;

import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lsy
 * @version 1.0
 * @description: selectAll()测试用例
 * @date: 2021/5/8 21:50
 * @param: null
 * @return:
 */

// 声明IOC容器的加载
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceImplTest {
    // 注入
    @Resource
    private CategoryService categoryService;

    @Test
    public void selectAll() {
        List<Category> list = categoryService.selectAll();
        System.out.println(list);
    }
}