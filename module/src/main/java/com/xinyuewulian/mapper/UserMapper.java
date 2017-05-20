package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.UserBo;
import com.xinyuewulian.entity.User;
import com.xinyuewulian.vo.UserVo;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    List<UserBo> queryUserList(UserVo vo);
}