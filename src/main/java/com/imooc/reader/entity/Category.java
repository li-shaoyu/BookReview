package com.imooc.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName Category
 * @Description 创建图书分类实体类
 * @date 2021/5/8 20:58
 * @Param
 * @return
 */
// 映射对应的数据库
@TableName("category")
public class Category {
    // 映射对应的主键（id），类型自增
    @TableId(type = IdType.AUTO)
    private Long categoryId;

    // 映射对应的属性
    @TableField("category_name")
    private String categoryName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
