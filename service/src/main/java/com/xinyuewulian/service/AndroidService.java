package com.xinyuewulian.service;

import com.cuisongliu.springboot.core.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinyuewulian.entity.*;
import com.xinyuewulian.entity.System;
import com.xinyuewulian.mapper.*;
import com.xinyuewulian.vo.LoginApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AndroidService {

    @Autowired
    private SystemMapper systemMapper;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private PhoneLogMapper phoneLogMapper;

    @Transactional(readOnly = false)
    public LoginApiVo loginAndroid(String phoneSerial, String systemId){
        LoginApiVo loginApiVo = new LoginApiVo();
        if(phoneSerial!=null && systemId!=null){
            System system = new System();
            system.setSystemId(systemId);
            system.setSystemType(0);//安卓系统
            system =  systemMapper.selectOne(system);
            if(system!=null){
                Phone phone = new Phone();
                phone.setPhoneSerial(phoneSerial);
                phone = phoneMapper.selectOne(phone);
                if(null !=phone){
                    Integer userId = phone.getUserId();
                    User user = userMapper.selectByPrimaryKey(userId);
                    if(user!=null){
//                        PageHelper.orderBy("type_order");
                        List<Type> typeList  = typeMapper.queryTypeByUserId(userId);
                        if(typeList!=null && typeList.size() > 0){
                            loginApiVo.setUser(user);
                            loginApiVo.setTypeList(typeList);
                            Param param = new Param();
                            param.setParamCode("QR_PREFIX");
                            param = paramMapper.selectOne(param);
                            if(param!=null && StringUtil.isNotEmptyIgnoreBlank(param.getParamValue())){
                                loginApiVo.setQrPrefix(param.getParamValue());
                                loginApiVo.setErrorCode("000000");
                                loginApiVo.setErrorMsg("该手持机序列与用户[%s]已经维护,请选择类型后扫码操作.");
                            }else {
                                loginApiVo.setErrorCode("000005");
                                loginApiVo.setErrorMsg("参数获取异常,请联系管理员.");
                            }
                        }else{
                            loginApiVo.setErrorCode("000004");
                            loginApiVo.setErrorMsg("该手持机序列已经维护操作类型未绑定,请联系管理员维护后操作.");
                        }
                    }else {
                        loginApiVo.setErrorCode("000003");
                        loginApiVo.setErrorMsg("该手持机未与用户进行绑定,请联系管理员维护后操作.");
                    }
                }else{
                    DataSourceTransactionManager manager = new DataSourceTransactionManager();
                    PhoneLog phoneLog = new PhoneLog();
                    phoneLog.setPhoneSerialId(phoneSerial);//手机序列
                    phoneLog.setPhoneSystemId(systemId);//系统Id
                    Page<?> page= PageHelper.startPage(1,-1);
                    phoneLogMapper.selectOne(phoneLog);
                    if(page.getTotal()  < 1 ){
                        //没有记录 则新增到记录表中去
                        phoneLog.setCreateDate(new Date());
                        phoneLogMapper.insert(phoneLog);
                    }
                    loginApiVo.setErrorCode("000002");
                    loginApiVo.setErrorMsg("该手持机序列未进行后台维护,请联系管理员维护后操作.");
                }
            }else{
                loginApiVo.setErrorCode("000001");
                loginApiVo.setErrorMsg("该手持机APP未绑定接口,请联系管理员维护后操作.");
            }
        }else {
            loginApiVo.setErrorCode("000006");
            loginApiVo.setErrorMsg("接口参数异常.");
        }
        return loginApiVo;
    }

    @Transactional(readOnly = false)
    public int insertRecord(String addStr, Integer operatorTypeId, Integer storeId, Integer userId,String company) {
        Record record = new Record();
        record.setRecordAddress(addStr);
        record.setRecordDate(new Date());
        record.setRecordOperator(userId);
        record.setRecordStore(storeId);
        record.setRecordType(operatorTypeId);
        if(StringUtil.isNotEmptyIgnoreBlank(company)){
            record.setRecordCompany(company);
        }
        return recordMapper.insert(record);
    }

}
