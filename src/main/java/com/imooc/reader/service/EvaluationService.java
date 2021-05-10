package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;

import java.util.List;

/**
 * @ClassName EvaluationService
 * @Description 短评服务接口
 * @date 2021/5/10 11:55
 * @Param
 * @return
 */
public interface EvaluationService {
    /**
     * 按图书编号查询有效短评
     * @param bookId 图书编号
     * @return 评论列表
     */
    public List<Evaluation> selectByBookId(Long bookId);
}
