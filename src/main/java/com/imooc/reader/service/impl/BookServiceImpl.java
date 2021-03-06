package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName BookServiceImp
 * @Description TODO
 * @date 2021/5/9 19:32
 * @Param
 * @return
 */
@Service("bookService")
// 声明式事务注解
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class BookServiceImpl implements BookService {
    //    注入BookMapper，其实现BaseMapper，有许多CURD方法
    @Resource
    private BookMapper bookMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Long categoryId , String order ,Integer page, Integer rows) {
        // p： 查询的页数
        Page<Book> p = new Page<Book>(page, rows);
        // queryWrapper: 条件构造器
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();

        // 表示从前台传入了有效的编号
        if (categoryId != null && categoryId != -1) {
            queryWrapper.eq("category_id", categoryId);
        }
        // 排序方式
        if (order != null) {
            // quantity： 按评价人数进行排序
            if (order.equals("quantity")) {
                queryWrapper.orderByDesc("evaluation_quantity");
            } else if (order.equals("score")) {
                // score： 按评分进行排序
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        // 核心方法 selectPage：实现分页查询
        // 两个参数：p   queryWrapper
        IPage<Book> pageObject = bookMapper.selectPage(p, queryWrapper);
        return pageObject;
    }

    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    public Book selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }

    /**
     * 更新图书评分/评价数量
     */
    // 开启声明式事务
    @Transactional
    public void updateEvaluation() {
        bookMapper.updateEvaluation();
    }

    /**
     * 新增图书
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    /**
     * 更新图书
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Transactional
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    /**
     * 删除图书及相关数据
     * @param bookId 图书编号
     */
    @Transactional
    public void deleteBook(Long bookId) {
        // 1、 删除book表的数据
        bookMapper.deleteById(bookId);

        // 2、 删除阅读状态表的数据
        QueryWrapper<MemberReadState> mrsQueryWrapper = new QueryWrapper<MemberReadState>();
        mrsQueryWrapper.eq("book_id", bookId);
        memberReadStateMapper.delete(mrsQueryWrapper);

        // 3、 删除评论表格的数据
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<Evaluation>();
        evaluationQueryWrapper.eq("book_id", bookId);
        evaluationMapper.delete(evaluationQueryWrapper);

    }

}
