package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.SerialTypeBo;
import com.xinyuewulian.entity.SerialType;

import java.util.List;

public interface SerialTypeMapper extends MyMapper<SerialType> {

    public List<SerialTypeBo> querySerialTypeList();
}