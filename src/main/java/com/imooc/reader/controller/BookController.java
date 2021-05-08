package com.imooc.reader.controller;

import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BookController
 * @Description 图书控制类
 * @date 2021/5/8 21:54
 * @Param
 * @return
 */
@Controller
public class BookController {
    @Resource
    private CategoryService categoryService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView showIndex(){
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList", categoryList);
        return mav;
    }
}
