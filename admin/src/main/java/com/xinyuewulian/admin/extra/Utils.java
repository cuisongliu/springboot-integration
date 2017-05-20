package com.xinyuewulian.admin.extra;

import com.cuisongliu.springboot.core.util.encrypt.MD5;

/**
 * 工具类
 *
 * @author cuisongliu
 */
public class Utils {

    /**
     * 获取密码密文
     * @param password
     * @return
     */
    public static   String getPassword(String password){
        return MD5.getMD5Str(MD5.getMD5Str(password)+ Globals.LOGIN_KEY);
    }
}
