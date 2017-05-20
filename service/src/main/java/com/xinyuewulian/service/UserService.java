package com.xinyuewulian.service;

import com.cuisongliu.springboot.core.biz.BaseBiz;
import com.xinyuewulian.bo.UserBo;
import com.xinyuewulian.entity.Phone;
import com.xinyuewulian.entity.Store;
import com.xinyuewulian.entity.User;
import com.xinyuewulian.entity.UserType;
import com.xinyuewulian.mapper.PhoneMapper;
import com.xinyuewulian.mapper.StoreMapper;
import com.xinyuewulian.mapper.UserMapper;
import com.xinyuewulian.mapper.UserTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService extends BaseBiz<User>  {
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void modifyUser(UserBo userBo) {
        User u  = new User();
        BeanUtils.copyProperties(userBo,u);
        //手持机
        if(userBo.getPhoneArr() != null && userBo.getPhoneArr().length >0 ){
            //手持机不能为空
            List<Integer> phoneList = Arrays.asList(userBo.getPhoneArr());
            for (Integer phoneId :  phoneList){
                Phone tempPhone = phoneMapper.selectByPrimaryKey(phoneId);
                //已经选择的手机不能为空
                if(userBo.getPhone()!=null && userBo.getPhone().length >0 ){
                    //已经选择的手机列表
                    List<Integer> phone =Arrays.asList(userBo.getPhone());
                    if(phone.contains(phoneId)){
                        tempPhone.setUserId(userBo.getId());
                    }else {
                        tempPhone.setUserId(null);
                    }
                }else {
                    tempPhone.setUserId(null);
                }
                phoneMapper.updateByPrimaryKey(tempPhone);
            }
        }
        //货物
        if(userBo.getStoreArr() != null && userBo.getStoreArr().length >0){
            //货物不能为空
            List<Integer> storeList = Arrays.asList(userBo.getStoreArr());
            for (Integer storeId :  storeList){
                Store tempStore = storeMapper.selectByPrimaryKey(storeId);
                //已经选择的货物不能为空
                if(userBo.getStore()!=null  && userBo.getStore().length >0){
                    //已经选择的货物列表
                    List<Integer> store =Arrays.asList(userBo.getStore());
                    if(store.contains(storeId)){
                        tempStore.setUserId(userBo.getId());
                    }else {
                        tempStore.setUserId(null);
                    }
                }else {
                    tempStore.setUserId(null);
                }
                storeMapper.updateByPrimaryKey(tempStore);
            }
        }

        if(userBo.getTypeArr() != null && userBo.getTypeArr().length >0 && !isModify(userBo.getId(),userBo.getType())){
            //全部删除
            userTypeMapper.delByUserId(userBo.getId());
            //类型不能为空
            if(null != userBo.getType() && userBo.getType().length >0){
                List<Integer> typeList = Arrays.asList(userBo.getType());
                for (Integer typeId :  typeList){
                    UserType ut = new UserType();
                    ut.setUserId(userBo.getId());
                    ut.setTypeId(typeId);
                    userTypeMapper.insertSelective(ut);
                }
            }

        }
        userMapper.updateByPrimaryKeySelective(userBo);
    }

    private boolean isModify(Integer userId,Integer[] typeIds){
        UserType ut = new UserType();
        ut.setUserId(userId);
        List<UserType> utList = userTypeMapper.select(ut);
        boolean flag =false;
        if(typeIds== null || typeIds.length ==0){
            if(utList==null || utList.size() ==0){
                flag= true;
            }
        }else {
            if(utList==null){
                flag= false;
            }else if(utList!=null && utList.size() !=typeIds.length){
                flag= false;
            }else if(utList!=null && utList.size() ==typeIds.length){
                for (Integer typeId : typeIds){
                    ut.setTypeId(typeId);
                    UserType utTemp = userTypeMapper.selectOne(ut);
                    if(utTemp!=null){
                        flag= true;
                        continue;
                    }else {
                        flag= false;
                        break;
                    }
                }
            }

        }
        return flag;
    }
}
