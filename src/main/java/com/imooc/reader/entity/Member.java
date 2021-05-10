package com.imooc.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @ClassName Member
 * @Description 会员实体类
 * @date 2021/5/10 20:07
 * @Param
 * @return
 */
// 映射对应数据表
// MySql8的小伙伴注意一下，member被选为保留字段，避免冲突，可以加上反引号
@TableName("member")
public class Member {
    // 会员编号
    @TableId(type = IdType.AUTO)
    private Long memberId;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 盐值
    private Integer salt;
    // 昵称
    private String nickname;
    // 创建时间
    private Date createTime;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
