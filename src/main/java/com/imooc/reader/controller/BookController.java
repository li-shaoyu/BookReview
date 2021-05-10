package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BookController
 * @Description 图书控制类，接收前端请求
 * @date 2021/5/8 21:54
 * @Param
 * @return
 */
@Controller
public class BookController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/")

    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    /**
     * 分页查询图书列表
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param p 页号
     * @return 分页对象
     */
    // 绑定URL
    @GetMapping("/books")
    // 使用springMVC对这个IPage对象进行JSON序列化输出
    @ResponseBody
    public IPage<Book> selectBook(Long categoryId , String order ,Integer p) {
        // 容错处理： 若前台没有传入页号，默认第一页
        if (p == null) {
            p = 1;
        }
        // p: 前台传过来的页号
        IPage<Book> pageObject = bookService.paging(categoryId , order , p, 10);
        return pageObject;
    }
}
