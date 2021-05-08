package com.imooc.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.reader.entity.Test;

/**
 * @ClassName TestMapper
 * @Description TODO
 * @date 2021/5/7 17:07
 * @Param
 * @return
 */
// BaseMapper核心父接口，内有增删改查的方法
public interface TestMapper extends BaseMapper<Test> {
    // 自定义方法
    public void insertSample();
}
