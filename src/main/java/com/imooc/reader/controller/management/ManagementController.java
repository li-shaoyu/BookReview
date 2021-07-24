package com.imooc.reader.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className: ManagementController
 * @author: 李绍宇
 * @description: 后台管理系统控制器
 * @date: 2021/7/24 17:22
 * @version: 1.0
 */
@Controller
@RequestMapping("/management")
public class ManagementController {
    @GetMapping("/index.html")
    public ModelAndView showIndex(){
        return new ModelAndView("/management/index");
    }
}
