package com.xinyuewulian.service;

import com.xinyuewulian.entity.Param;
import com.xinyuewulian.mapper.ParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapService {

    @Autowired
    private ParamMapper paramMapper;

    public Map<String, String> getParamMap() {
        Map<String, String>  paramMap = new HashMap<>();
        List<Param>  params = paramMapper.selectAll();
        for (Param param : params){
            paramMap.put(param.getParamCode(),param.getParamValue());
        }
        return paramMap;
    }
}
