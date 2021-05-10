package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EvaluationServiceImpl
 * @Description 实现短评服务接口
 * @date 2021/5/10 11:56
 * @Param
 * @return
 */

@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private MemberMapper memberMapper;

    /**
     * 按图书编号查询有效短评
     *
     * @param bookId 图书编号
     * @return 评论列表
     */
    public List<Evaluation> selectByBookId(Long bookId) {

        Book book = bookMapper.selectById(bookId);

        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<Evaluation>();
        // 首先对BookId进行筛选
        queryWrapper.eq("book_id", bookId);
        // 筛选有效短评
        queryWrapper.eq("state", "enable");
        // 按创建时间降序排序
        queryWrapper.orderByDesc("create_time");

        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);

        // 在获取到对应的 evaluationList 集合后，提取每一个评论对象，查询该评论所对应的会员、图书信息
        for(Evaluation eva:evaluationList){
            Member member = memberMapper.selectById(eva.getMemberId());
            eva.setMember(member);
            eva.setBook(book);
        }

        return evaluationList;
    }
}
