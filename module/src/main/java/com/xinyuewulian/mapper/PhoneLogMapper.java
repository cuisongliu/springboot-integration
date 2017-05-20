package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.PhoneLogBo;
import com.xinyuewulian.entity.PhoneLog;

import java.util.List;

public interface PhoneLogMapper extends MyMapper<PhoneLog> {

    public List<PhoneLog>   queryPhoneSelectList();

    public List<PhoneLogBo>   queryPhoneSelectAllList();
}