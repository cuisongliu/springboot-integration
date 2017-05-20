package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.PhoneBo;
import com.xinyuewulian.entity.Phone;
import com.xinyuewulian.vo.QueryVo;

import java.util.List;

public interface PhoneMapper extends MyMapper<Phone> {

    List<PhoneBo> queryPhoneList(QueryVo vo);
}