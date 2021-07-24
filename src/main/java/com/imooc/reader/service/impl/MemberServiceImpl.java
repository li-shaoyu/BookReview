package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Member;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.service.exception.BussinessException;
import com.imooc.reader.service.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @className: MemberServiceImpl
 * @author: 李绍宇
 * @description: 会员注册登录服务实现类
 * @date: 2021/7/24 10:37
 * @version: 1.0
 */
@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;
//    @Resource
//    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    /**
     * 会员注册,创建新会员
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新会员对象
     */
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username", username);
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        // 1、 判断用户名是否已存在
        if(memberList.size() > 0){
            throw new BussinessException("M01","用户名已存在");
        }
        // 2、 不存在则保存新用户
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);

        // 3、 保存到数据库
        // 产生盐值 （1000-1999）
        int salt = new Random().nextInt(1000) + 1000;
        // MD5摘要处理
        String md5 = MD5Utils.md5Digest(password, salt);
        // 保存加密后的密码、盐值和创建时间
        member.setPassword(md5);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    /**
     * 登录检查
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录对象
     */
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username", username);

        // 1、 用户名校验
        // selectOne（）查询用户名，全局唯一
        Member member = memberMapper.selectOne(queryWrapper);
        if(member == null){
            throw new BussinessException("M02", "用户不存在");
        }

        // 2、 密码校验
        String md5 = MD5Utils.md5Digest(password, member.getSalt());
        if(!md5.equals(member.getPassword())){
            throw new BussinessException("M03", "输入密码有误");
        }
        return member;
    }

}

