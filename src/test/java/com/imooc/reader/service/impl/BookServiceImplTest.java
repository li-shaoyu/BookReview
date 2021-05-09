package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

// IOC容器初始化
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Resource
    private BookService bookService;

    @Test
    public void paging() {
        IPage<Book> pageObject = bookService.paging(2l,"quantity" ,2, 10);
        // getRecords: 分页对象记录列表
        List<Book> records = pageObject.getRecords();
        // 增强for循环
        for(Book b:records){
            System.out.println(b.getBookId() + ":" + b.getBookName());
        }
        System.out.println("总页数:" + pageObject.getPages());
        System.out.println("总记录数:" + pageObject.getTotal());
    }
}

