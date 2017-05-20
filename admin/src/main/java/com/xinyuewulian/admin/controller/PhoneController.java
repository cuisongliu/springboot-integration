package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.PhoneBo;
import com.xinyuewulian.entity.Phone;
import com.xinyuewulian.mapper.PhoneLogMapper;
import com.xinyuewulian.mapper.PhoneMapper;
import com.xinyuewulian.mapper.UserMapper;
import com.xinyuewulian.service.PhoneService;
import com.xinyuewulian.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController extends BaseController implements Globals{

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private PhoneLogMapper phoneLogMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String phoneList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        String keyWords = request.getParameter("keyWords");
        QueryVo queryVo = new QueryVo();
        queryVo.setKeyWords(keyWords);
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
//        PageHelper.orderBy("id desc");
        modifyModel("手持机管理","手持机的列表","显示所有的手持机信息.",model);
        List<PhoneBo> list  = phoneMapper.queryPhoneList(queryVo);
        PageInfo<PhoneBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("contentUrl", UrlEnum.PHONE_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.PHONE_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.PHONE_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.PHONE_GET_URL.getUrl());
        return  "phone/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String phoneDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        phoneService.delete(id);
        return  phoneList(model,request,pageSize,currNo);
    }

    private void  initView(Model model){
        model.addAttribute("userMap",userMapper.selectAll());
        model.addAttribute("userTypeMap",Globals.MAP_USER_HASH_TYPE);
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String phoneAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("手持机管理","新增手持机","新增手持机,从待选择列表选择.",model);
        initView(model);
        model.addAttribute("saveUrl", UrlEnum.PHONE_ADD_SAVE_URL.getUrl());
        model.addAttribute("phoneSerialValidateUrl", UrlEnum.PHONE_PHONESERIAL_VALIDATION_URL.getUrl());
        model.addAttribute("phoneSelectMap",phoneLogMapper.queryPhoneSelectList());
        return  "phone/add";
    }

    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String phoneEditSave(HttpServletRequest request, Phone phone) throws IOException {
        phoneService.save(phone);
        return  "redirect:"+ UrlEnum.PHONE_LIST_URL.getUrl()+"10/1";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String phoneValidation(Model model, HttpServletRequest request,@PathVariable("column") String column){
        if(column.equals("phoneSerial")){
            String phoneSerial = request.getParameter("phoneSerial");
            Phone phone = new Phone();
            phone.setPhoneSerial(phoneSerial);
            Page<?> page = PageHelper.startPage(1,-1);
            phoneMapper.select(phone);
            if(page.getTotal() > 0 ){
                return "false";
            }else {
                return "true";
            }
        }
        return  "false";
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String phoneEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        Phone phone = phoneService.getByID(id);
        modifyModel("参数信息维护","修改参数信息","管理员对参数信息进行修改操作.",model);
        initView(model);
        model.addAttribute("entity",phone);
        model.addAttribute("editUrl", UrlEnum.PHONE_EDIT_SAVE_URL.getUrl());
        return  "phone/edit";
    }


    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String phoneEditCurrent(Model model, HttpServletRequest request, Phone entity  ){
        Phone temp = phoneMapper.selectByPrimaryKey(entity.getId());
        temp.setUserId(entity.getUserId());
        temp.setPhoneName(entity.getPhoneName());
        temp.setPhoneType(entity.getPhoneType());
        phoneService.updateEntity(temp);
        return  "redirect:"+ UrlEnum.PHONE_LIST_URL.getUrl()+"10/1";
    }

}
