package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.SerialTypeBo;
import com.xinyuewulian.entity.SerialType;
import com.xinyuewulian.mapper.SerialTypeMapper;
import com.xinyuewulian.service.SerialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/serial/type")
public class SerialTypeController extends BaseController implements Globals{

    @Autowired
    private SerialTypeService serialTypeService;
    @Autowired
    private SerialTypeMapper serialTypeMapper;

    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String serialTypeList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
        OrderByHelper.orderBy("id desc");
        modifyModel("序列类型信息维护","序列类型列表","显示所有的序列类型信息.",model);
        List<SerialTypeBo> list  = serialTypeMapper.querySerialTypeList();
        PageInfo<SerialTypeBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("contentUrl", UrlEnum.SERIAL_TYPE_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.SERIAL_TYPE_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.SERIAL_TYPE_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.SERIAL_TYPE_GET_URL.getUrl());
        model.addAttribute("stateMap", MAP_STATE_HASH_TYPE);
        return  "serial/type/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String serialTypeDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        serialTypeService.delete(id);
        return  "redirect:"+ UrlEnum.SERIAL_TYPE_LIST_URL.getUrl()+pageSize+"/"+currNo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String serialTypeAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("序列类型信息维护","新增序列类型","新增序列类型,设置序列类型信息.",model);
        model.addAttribute("saveUrl", UrlEnum.SERIAL_TYPE_ADD_SAVE_URL.getUrl());
        model.addAttribute("stateMap", MAP_STATE_LINKED_TYPE);
        model.addAttribute("serialTypeCodeValidateUrl", UrlEnum.SERIAL_TYPE_CODE_VALIDATION_URL.getUrl());
        return  "serial/type/add";
    }

    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String serialTypeEditSave(HttpServletRequest request, SerialType serialType) throws IOException {
        serialTypeService.save(serialType);
        return  "redirect:"+ UrlEnum.SERIAL_TYPE_LIST_URL.getUrl()+"10/1";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String serialTypeValidation(Model model, HttpServletRequest request,@PathVariable("column") String column){
        if(column.equals("serialTypeCode")){
            String serialTypeCode = request.getParameter("serialTypeCode");
            SerialType serialType = new SerialType();
            serialType.setSerialTypeCode(serialTypeCode);
            Page<?> page = PageHelper.startPage(1,-1);
            serialTypeMapper.select(serialType);
            if(page.getTotal() > 0 ){
                return "false";
            }else {
                return "true";
            }
        }
        return  "false";
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String serialTypeEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        SerialType serialType = serialTypeService.getByID(id);
        modifyModel("序列类型信息维护","修改序列类型信息","管理员对序列类型信息进行修改操作.",model);
        model.addAttribute("entity",serialType);
        model.addAttribute("editUrl", UrlEnum.SERIAL_TYPE_EDIT_SAVE_URL.getUrl());
        return  "serial/type/edit";
    }


    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String serialTypeEditCurrent(Model model, HttpServletRequest request, SerialType entity  ) throws IOException {
        serialTypeService.update(entity);
        return  "redirect:"+ UrlEnum.SERIAL_TYPE_LIST_URL.getUrl()+"10/1";
    }

}
