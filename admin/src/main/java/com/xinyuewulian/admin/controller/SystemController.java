package com.xinyuewulian.admin.controller;

import com.cuisongliu.springboot.core.util.DateUtil;
import com.cuisongliu.springboot.core.util.encrypt.BASE64URL;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.entity.System;
import com.xinyuewulian.mapper.SystemMapper;
import com.xinyuewulian.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController implements Globals{

    @Autowired
    private SystemMapper systemMapper;
    @Autowired
    private SystemService systemService;
    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String systemList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
        OrderByHelper.orderBy("id desc");
        modifyModel("系统接入维护","系统对接Key列表","显示所有的系统对接Key信息.",model);
        List<System> list  = systemMapper.selectAll();
        PageInfo<System> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("contentUrl", UrlEnum.SYSTEM_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.SYSTEM_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.SYSTEM_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.SYSTEM_GET_URL.getUrl());
        model.addAttribute("systemTypeMap",MAP_SYSTEM_HASH_TYPE);
        return  "system/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String systemDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        systemService.delete(id);
        return  "redirect:"+ UrlEnum.SYSTEM_LIST_URL.getUrl()+pageSize+"/"+currNo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String systemAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("系统接入维护","新增系统对接Key","新增系统对接Key,设置系统对接Key信息.",model);
        System system =new System();
        String dateStr = DateUtil.Date2String(new Date(),"yyyyHHmmMMssms"+new Random().nextInt(100));
        system.setSystemId(BASE64URL.base64UrlEncode(dateStr.getBytes()));
        system.setSystemCode(dateStr);
        model.addAttribute("saveUrl", UrlEnum.SYSTEM_ADD_SAVE_URL.getUrl());
        model.addAttribute("entity",system);
        model.addAttribute("systemTypeMap",Globals.MAP_SYSTEM_LINKED_TYPE);
        return  "system/add";
    }

    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String systemEditSave(HttpServletRequest request, System system) throws IOException {
        systemService.save(system);
        return  "redirect:"+ UrlEnum.SYSTEM_LIST_URL.getUrl()+"10/1";
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String systemEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        System system = systemService.getByID(id);
        modifyModel("系统接入维护","修改系统对接Key信息","管理员对系统对接Key信息进行修改操作.",model);
        model.addAttribute("entity",system);
        model.addAttribute("editUrl", UrlEnum.SYSTEM_EDIT_SAVE_URL.getUrl());
        model.addAttribute("systemTypeMap",MAP_SYSTEM_LINKED_TYPE);
        return  "system/edit";
    }


    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String systemEditCurrent(Model model, HttpServletRequest request, System entity  ) throws IOException {
        systemService.update(entity);
        return  "redirect:"+ UrlEnum.SYSTEM_LIST_URL.getUrl()+"10/1";
    }

}
