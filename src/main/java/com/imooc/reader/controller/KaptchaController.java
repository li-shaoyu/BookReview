package com.imooc.reader.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName KaptchaController
 * @Description 验证码组件控制器
 * @date 2021/5/10 21:37
 */
@Controller
public class KaptchaController {

    // 属性名kaptchaProducer 需要和applicationContext中定义的一致
    // Producer是一个接口
    @Resource
    private Producer kaptchaProducer;

    // 生成验证码图片
    // 包含两个原生对象   request和 response
    // SpringMVC 依然依赖于J2EE的web模块（Servlet）
    @GetMapping(value = "/verify_code")
    public void createVerifyCode(HttpServletRequest request , HttpServletResponse response) throws IOException {

        // 1、 前期配置
        // 过期时间，代表响应立即过期
        response.setDateHeader("Expires",0);
        // 缓存控制，清空浏览器缓存，不缓存任何图片数据
        // 因为要求每一次都是全新的
        response.setHeader("Cache-Control" , "no-store,no-cache,must-revalidate");
        // 兼容性考虑
        response.setHeader("Cache-Control" , "post-check=0,pre-check=0");
        response.setHeader("Pragma" , "no-cache");
        // 返回前端图片样式
        response.setContentType("image/png");

        // 2、 生成
        // 生成验证码字符文本
        String verifyCode = kaptchaProducer.createText();

        // 3、 保存
        // 保存字符码到Session中
        request.getSession().setAttribute("kaptchaVerifyCode",verifyCode);
        // 打印出来，方便调试
        System.out.println(request.getSession().getAttribute("kaptchaVerifyCode"));

        // 4、 输出
        // createImage（）： 创建验证码图片
        // 将生成的verifyCode字符传入图片，二进制图片
        BufferedImage image = kaptchaProducer.createImage(verifyCode);
        // 输出流
        ServletOutputStream out = response.getOutputStream();

        // 输出图片流
        // 将生成的image 放入输出流out中，格式：png
        ImageIO.write(image, "png", out);

        // 立即输出
        out.flush();
        // 关闭输出流
        out.close();
    }

}
