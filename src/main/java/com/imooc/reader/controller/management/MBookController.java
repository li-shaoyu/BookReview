package com.imooc.reader.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

}
