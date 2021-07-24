package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.*;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.service.EvaluationService;
import com.imooc.reader.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    // 注入BookService，实则是BookService的实现类BookServiceImpl，其实现了BookService接口的两个方法
    @Resource
    private BookService bookService;

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private MemberService memberService;

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

    // URL绑定，使用springMVC路径变量进行捕获
    @GetMapping("/book/{id}")
    // @PathVariable： 路径变量
    public ModelAndView showDetail(@PathVariable("id") Long id, HttpSession session) {
        // 通过selectById(id)获取图书对象
        Book book = bookService.selectById(id);

        // 把具体的id传入，得到评论列表
        List<Evaluation> evaluationList = evaluationService.selectByBookId(id);

        // 当用户点开一本书时，就要查询该用户对于这本书的阅读状态
        Member member = (Member)session.getAttribute("loginMember");

        // 跳转到名字为detail的freemaker页面
        ModelAndView mav = new ModelAndView("/detail");

        if(member != null){
            // 获取会员阅读状态
            MemberReadState memberReadState = memberService.selectMemberReadState(member.getMemberId(), id);
            mav.addObject("memberReadState", memberReadState);
        }

        // 将查询到的对象放入到ModelAndView中
        mav.addObject("book", book);
        mav.addObject("evaluationList", evaluationList);

        // 返回mav结果给前端（处理器适配器）
        return mav;
    }


}
