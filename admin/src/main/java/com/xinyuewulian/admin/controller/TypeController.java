package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.TypeBo;
import com.xinyuewulian.entity.Type;
import com.xinyuewulian.mapper.TypeMapper;
import com.xinyuewulian.service.TypeService;
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
@RequestMapping(value = "/type")
public class TypeController extends BaseController implements Globals{

    @Autowired
    private TypeMapper typeMapper;
//    @Autowired
//    private UserTypeMapper userTypeMapper;
    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String typeList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo)  {
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
        OrderByHelper.orderBy("type_order ,id desc");
        modifyModel("操作类型维护","操作类型列表","显示所有的操作类型信息.",model);
        List<TypeBo> list  =  typeMapper.queryTypeListByModify(null);
        PageInfo<TypeBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("loginInfo",getLoginInfo(request));
        model.addAttribute("contentUrl", UrlEnum.TYPE_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.TYPE_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.TYPE_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.TYPE_GET_URL.getUrl());
        return  "type/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String typeDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id) throws IOException {
        typeService.delete(id);
        return  typeList(model,request,pageSize,currNo);
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String typeAdd(Model model, HttpServletRequest request)  {
        modifyModel("操作类型维护","新增操作类型","新增操作类型,设置操作类型的名称、排序、编码等.",model);
        model.addAttribute("saveUrl", UrlEnum.TYPE_ADD_SAVE_URL.getUrl());
        model.addAttribute("typeCodeValidateUrl", UrlEnum.TYPE_TYPECODE_VALIDATION_URL.getUrl());
        return  "type/add";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String typeValidation(Model model, HttpServletRequest request,@PathVariable("column") String column) throws IOException {
        if(column.equals("typeCode")){
        }
        return  "false";
    }


    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String typeEditSave(HttpServletRequest request, Type type)   {
        typeService.save(type);
        return  "redirect:"+ UrlEnum.TYPE_LIST_URL.getUrl()+"10/1";
    }



    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String typeEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) throws IOException {
        Type type = typeService.getByID(id);
        modifyModel("操作类型维护","修改操作类型","管理员对操作类型进行修改,可设置操作类型的名称、排序、编码等.",model);
        model.addAttribute("entity",type);
        model.addAttribute("editUrl", UrlEnum.TYPE_EDIT_SAVE_URL.getUrl());
        return  "type/edit";
    }

    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String typeEditSave(Model model,HttpServletRequest request,Type type)  {
        typeService.update(type);
        return  typeList(model,request,10,1);
    }

}
