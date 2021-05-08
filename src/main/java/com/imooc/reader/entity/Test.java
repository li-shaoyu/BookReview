package com.imooc.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName Test
 * @Description 实体类
 * @date 2021/5/8 8:46
 * @Param
 * @return
 */

// 说明实体对应哪一张表
@TableName("test")
public class Test {
    // 声明表的主键，type：主键生成方式
    @TableId(type = IdType.AUTO)
    // 说明属性对应哪个字段
    @TableField("id")
    private Integer id;

    // 如果字段名与属性名相同或者符合驼峰命名转换规则,则TableField可省略
    // 1. 字段名与属性名相同: content
    // 2. 符合驼峰命名转换规则： abc_abc   abcAbc
    @TableField("content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
