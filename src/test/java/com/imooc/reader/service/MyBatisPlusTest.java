package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Test;
import com.imooc.reader.mapper.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MyBatisPlusTest
 * @Description MyBatisPlus测试用例
 * @date 2021/5/8 9:03
 * @Param
 * @return
 */
// 初始化IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
// 指向要加载的配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyBatisPlusTest {

    // 注入配置好的接口
    @Resource
    private TestMapper testMapper;

    // 增加测试
    @org.junit.Test
    public void testInsert() {
        Test test = new Test();
        test.setContent("MyBatis Plus增加测试");
        testMapper.insert(test);
    }

    // 更新测试
    @org.junit.Test
    public void testUpdate() {
        Test test = testMapper.selectById(9);
        test.setContent("MyBatis Plus更新测试");
        testMapper.updateById(test);
    }

    // 删除测试
    @org.junit.Test
    public void testDelete() {
        testMapper.deleteById(9);
    }

    // 查找测试
    @org.junit.Test
    public void testSelect() {
        QueryWrapper<Test> queryWrapper = new QueryWrapper<Test>();
        queryWrapper.eq("id", 7);
        queryWrapper.gt("id", 5);
        List<Test> list = testMapper.selectList(queryWrapper);
        System.out.println(list.get(0));
    }
}

