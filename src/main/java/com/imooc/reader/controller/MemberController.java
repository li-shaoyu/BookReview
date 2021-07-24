package com.imooc.reader.controller;

import com.imooc.reader.entity.Member;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: MemberController
 * @author: 李绍宇
 * @description: 会员控制器
 * @date: 2021/7/24 9:15
 * @version: 1.0
 */
@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    // 映射路径
    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        // register对应脚本register.ftl
        return new ModelAndView("/register");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/login");
    }

    @PostMapping("/registe")
    @ResponseBody
    public Map registe(String vc, String username, String password, String nickname, HttpServletRequest request) {
        // 正确验证码
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        // 验证码对比
        Map result = new HashMap();
        // vc.equalsIgnoreCase() 忽略大小写
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                memberService.createMember(username, password, nickname);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BussinessException ex) {
                // 打印堆栈
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg", ex.getMsg());
            }
        }
        return result;
    }

    @PostMapping("/check_login")
    @ResponseBody
    // 原生对象：HttpSession session
    public Map checkLogin(String username, String password, String vc , HttpSession session){
        // 1、 验证码校验
        String verifyCode = (String)session.getAttribute("kaptchaVerifyCode");
        // 验证码对比
        Map result = new HashMap();
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        }else{
            try {
                // 2、 登录校验
                Member member = memberService.checkLogin(username, password);
                // 登录校验成功后，保存用户数据到Session，用于成功后首页右上角展示
                session.setAttribute("loginMember" , member);
                result.put("code", "0");
                result.put("msg", "success");
            }catch (BussinessException ex){
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg", ex.getMsg());
            }
        }
        return result;
    }


}
