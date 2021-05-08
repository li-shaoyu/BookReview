package com.imooc.reader.service;

import com.imooc.reader.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName TestService
 * @Description 测试类
 * @date 2021/5/7 17:11
 * @Param
 * @return
 */

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    // 事务控制注解：运行成功则进行全局提交，失败则全局回滚
    // 要么全部要么什么都不做
    // 这让我想起了初中充值饭卡的经历： 一百元分两次五十充值，可是系统只记录了一次五十，找学校多次都无果
    @Transactional
    public void batchImport() {
        for (int i = 0; i < 5; i++) {
            // 事务提交测试
//            if (i == 3) {
//                throw new RuntimeException("预期外异常");
//            }
//            testMapper.insert();
        }
    }
}
