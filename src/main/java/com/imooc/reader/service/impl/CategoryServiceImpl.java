package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Category;
import com.imooc.reader.mapper.CategoryMapper;
import com.imooc.reader.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description 图书分类服务接口实现类
 * @date 2021/5/8 21:34
 * @Param
 * @return
 */
// 注意一下 bean的ID，保持与服务接口一致
// 面向接口编程，隐藏底层类的创建
@Service("categoryService")
// 事务设置，readOnly 只读，表明不需要事务处理
// 事务的开启取决与业务： 查询可以不需要，插入需要
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    // 注入前面写好的接口
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询所有图书分类
     *
     * @return 图书分类List
     */
    public List<Category> selectAll() {
        // selectList: 查询多个列表，返回多个数据
        // QueryWrapper: 条件构造器
        List<Category> list = categoryMapper.selectList(new QueryWrapper<Category>());
        return list;
    }
}
