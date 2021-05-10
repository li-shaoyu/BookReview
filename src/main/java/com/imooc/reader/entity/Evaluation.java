package com.imooc.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @ClassName Evaluation
 * @Description 评论信息实体类
 * @date 2021/5/10 11:45
 * @Param
 * @return
 */
// 映射数据库表格evaluation
@TableName("evaluation")
public class Evaluation {
    // 映射主键id，自增
    @TableId(type= IdType.AUTO)
    // 评论编号
    private Long evaluationId;
    // 图书编号
    private Long bookId;
    // 短评内容
    private String content;
    // 评分（5分制）
    private Integer score;
    // 会员编号
    private Long memberId;
    // 创建时间
    private Date createTime;
    // 点赞数量
    private Integer enjoy;
    // 审核状态 enable-有效 disable-禁用
    private String state;
    // 禁用理由
    private String disableReason;
    // 禁用时间
    private Date disableTime;

    // book属性具有Book的关联对象
    // exist说明book属性没有对应字段,不会参与到sql语句自动生成，需要手动查询
    @TableField(exist = false)
    private Book book;

    // 和book对象一样
    @TableField(exist = false)
    private Member member;

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(Integer enjoy) {
        this.enjoy = enjoy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
