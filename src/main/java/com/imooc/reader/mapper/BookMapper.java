package com.imooc.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.reader.entity.Book;

/**
 * @ClassName BookMapper
 * @Description 图书接口类
 * @date 2021/5/9 19:25
 * @Param
 * @return
 */
public interface BookMapper extends BaseMapper<Book> {
    /**
     * 更新图书评分/评价数量
     */
    public void updateEvaluation();
}

