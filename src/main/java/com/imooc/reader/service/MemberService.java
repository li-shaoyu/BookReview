package com.imooc.reader.service;

import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;

/**
 * @className: MemberService
 * @author: 李绍宇
 * @description: 会员服务
 * @date: 2021/7/24 10:35
 * @version: 1.0
 */

public interface MemberService {
    /**
     * 会员注册,创建新会员
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新会员对象
     */
    public Member createMember(String username, String password, String nickname);

    /**
     * 登录检查
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录对象
     */
    public Member checkLogin(String username, String password);

    /**
     * 获取阅读状态
     *
     * @param memberId 会员编号
     * @param bookId   图书编号
     * @return 阅读状态对象
     */
    public MemberReadState selectMemberReadState(Long memberId, Long bookId);

    /**
     * 更新阅读状态
     *
     * @param memberId  会员编号
     * @param bookId    图书编号
     * @param readState 阅读状态
     * @return 阅读状态对象
     */
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState);


}
