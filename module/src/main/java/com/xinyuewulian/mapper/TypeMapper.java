package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.TypeBo;
import com.xinyuewulian.entity.Type;

import java.util.List;

public interface TypeMapper extends MyMapper<Type> {

    public List<Type> queryTypeByUserId(Integer userId);
    public List<TypeBo> queryTypeListByModify(Integer id);
}