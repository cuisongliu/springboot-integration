package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.RecordBo;
import com.xinyuewulian.entity.Record;
import com.xinyuewulian.vo.RecordVo;

import java.util.List;

public interface RecordMapper extends MyMapper<Record> {

    List<RecordBo> queryRecordList(RecordVo vo);
}