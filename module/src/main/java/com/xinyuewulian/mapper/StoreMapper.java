package com.xinyuewulian.mapper;

import com.cuisongliu.springboot.core.mapper.MyMapper;
import com.xinyuewulian.bo.StoreBo;
import com.xinyuewulian.entity.Store;
import com.xinyuewulian.vo.StoreVo;

import java.util.List;

public interface StoreMapper extends MyMapper<Store> {
    public List<Store> queryStoreByUserId(Integer userId);
    public List<StoreBo> queryStoreList(StoreVo storeVo);
}