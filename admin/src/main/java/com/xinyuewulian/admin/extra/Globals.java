package com.xinyuewulian.admin.extra;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cuisongliu on 2016/11/22.
 *
 * @author cuisongliu
 */
public interface Globals {

    Integer USER_TYPE_ADMIN = 0;
    Integer USER_TYPE_USER = 1;

    String SESSION_KEY = "session_admin";
    String ERROR_MSG = "session_err_msg";
    String LOGIN_KEY = "login_xinyuewulian";

    /**
     * 显示内容
     */
    Map<Integer,String> MAP_USER_HASH_TYPE = new HashMap<Integer,String>(){
        {
            put(USER_TYPE_ADMIN,"管理员");
            put(USER_TYPE_USER,"普通用户");
        }
    };

    /**
     * 下拉列表
     */
    Map<Integer,String> MAP_USER_LINKED_TYPE = new LinkedHashMap<>(MAP_USER_HASH_TYPE);


    /**
     * 显示内容
     */
    Map<Integer,String> MAP_PARAM_HASH_TYPE = new HashMap<Integer,String>(){
        {
            put(0,"可编辑可删除");
            put(1,"可编辑不可删除");
            put(2,"只读");
        }
    };

    /**
     * 下拉列表
     */
    Map<Integer,String> MAP_PARAM_LINKED_TYPE = new LinkedHashMap<>(MAP_PARAM_HASH_TYPE);

    /**
     * 显示内容
     */
    Map<Integer,String> MAP_SYSTEM_HASH_TYPE = new HashMap<Integer,String>(){
        {
            put(0,"Android系统");
            put(1,"IOS系统");
        }
    };

    /**
     * 下拉列表
     */
    Map<Integer,String> MAP_SYSTEM_LINKED_TYPE = new LinkedHashMap<>(MAP_SYSTEM_HASH_TYPE);


    /**
     * 显示内容
     */
    Map<Integer,String> MAP_STATE_HASH_TYPE = new HashMap<Integer,String>(){
        {
            put(1,"启用");
            put(-1,"停用");
        }
    };

    /**
     * 下拉列表
     */
    Map<Integer,String> MAP_STATE_LINKED_TYPE = new LinkedHashMap<>(MAP_STATE_HASH_TYPE);
}
