package com.imooc.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.exception.BussinessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: MBookController
 * @author: 李绍宇
 * @description: TODO
 * @date: 2021/7/24 15:03
 * @version: 1.0
 */
@Controller
@RequestMapping("/management/book")
public class MBookController {

    @Resource
    private BookService bookService;

    // 图书管理功能
    @GetMapping("/index.html")
    public ModelAndView showBook(){
        return new ModelAndView("/management/book");
    }

    /**
     * wangEditor图片上传
     * @param file 上传图片
     * @param request 原生请求对象
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    // JSON序列化输出
    @ResponseBody
    // @RequestParam("img")的img与前端设置的图片上传参数保持一致
    public Map upload(@RequestParam("img") MultipartFile file , HttpServletRequest request) throws IOException {
        // 获取upload目录的路径
        String uploadPath = request.getServletContext().getResource("/").getPath() + "/upload/";
        // 生成文件名
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // 文件扩展名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 保存上传的文件到upload目录
        file.transferTo(new File(uploadPath + fileName + suffix));
        // 返回保存成功的结果给前端
        Map result = new HashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + fileName + suffix});
        return result;
    }

    /**
     * 新增图书
     * @param book
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map createBook(Book book){
        Map result = new HashMap();
        try {
            // 1、 设置默认评价人数和分数
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);

            // 2、 解析图书详情
            // JavaHTML解析器Jsoup，提取需要的数据
            Document doc = Jsoup.parse(book.getDescription());

            // 获取图书详情第一图的元素对象
            Element img = doc.select("img").first();

            // attr获取当前元素的指定值
            String cover = img.attr("src");

            // 来自于description描述的第一幅图
            book.setCover(cover);

            // 3、 保存
            bookService.createBook(book);

            // 返回前端的信息
            result.put("code", "0");
            result.put("msg", "success");
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

    /**
     * 分页查询图书数据
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Map list(Integer page , Integer limit){
        // 默认查询第一页
        if(page == null){
            page = 1;
        }
        // 默认10行
        if(limit == null){
            limit = 10;
        }

        // 实现之前首页的分页接口，忽略数据类别和降序，设置为NULL
        IPage<Book> pageObject = bookService.paging(null, null, page, limit);
        // 返回的数据
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        // 当前页面数据
        result.put("data", pageObject.getRecords());
        // 未分页时记录总数
        result.put("count", pageObject.getTotal());

        return result;
    }


}
