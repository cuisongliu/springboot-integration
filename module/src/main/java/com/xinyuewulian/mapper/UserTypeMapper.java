package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.entity.UserType;

public interface UserTypeMapper extends MyMapper<UserType> {
    public void delByUserId(Integer userId);
}