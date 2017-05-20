package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.PhoneLogBo;
import com.xinyuewulian.mapper.PhoneLogMapper;
import com.xinyuewulian.service.PhoneLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/phone/select")
public class PhoneSelectController extends BaseController implements Globals{

    @Autowired
    private PhoneLogMapper phoneLogMapper;
    @Autowired
    private PhoneLogService phoneLogService;

    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String phoneSelectList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
//        PageHelper.orderBy("id desc");
        modifyModel("手持机序号管理","可选择手持机序号的列表","显示所有的手持机序号.",model);
        List<PhoneLogBo> list  = phoneLogMapper.queryPhoneSelectAllList();
        PageInfo<PhoneLogBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("contentUrl", UrlEnum.PHONE_SELECT_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.PHONE_SELECT_DEL_URL.getUrl());
        return  "phone/select/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String phoneSelectDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        phoneLogService.delete(id);
        return  phoneSelectList(model,request,pageSize,currNo);
    }


}
