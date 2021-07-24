package com.imooc.reader.task;

import com.imooc.reader.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @className: ComputeTask
 * @author: 李绍宇
 * @description: 完成自动计算任务
 * @date: 2021/7/24 14:34
 * @version: 1.0
 */
@Component
public class ComputeTask {
    @Resource
    private BookService bookService;
    // 任务调度: 每分钟更新
    @Scheduled(cron = "0 * * * * ?")
    public void updateEvaluation(){
        bookService.updateEvaluation();
        System.out.println("已更新所有图书评分");
    }
}
