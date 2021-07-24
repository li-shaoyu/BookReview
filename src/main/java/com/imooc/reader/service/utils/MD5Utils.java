package com.imooc.reader.service.utils;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * @className: MD5Utils
 * @author: 李绍宇
 * @description: MD5算法处理用户密码
 * @date: 2021/7/24 10:49
 * @version: 1.0
 */


public class MD5Utils {
    public static String md5Digest(String source , Integer salt){
        char[] ca = source.toCharArray();
        // 混淆源数据
        for(int i = 0 ; i < ca.length ; i++){
            ca[i] = (char) (ca[i] + salt);
        }
        String target = new String(ca);
        String md5 = DigestUtils.md5Hex(target);
        return md5;
    }
}