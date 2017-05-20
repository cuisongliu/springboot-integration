package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.entity.Param;
import com.xinyuewulian.mapper.ParamMapper;
import com.xinyuewulian.service.ParamService;
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
@RequestMapping(value = "/param")
public class ParamController extends BaseController implements Globals{

    @Autowired
    private ParamService paramService;
    @Autowired
    private ParamMapper paramMapper;

    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String paramList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        String keyWords = request.getParameter("keyWords");
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
//        PageHelper.orderBy("id desc");
        modifyModel("参数信息维护","参数列表","显示所有的参数信息.",model);
        List<Param> list  = paramMapper.selectAll();
        PageInfo<Param> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("contentUrl", UrlEnum.PARAM_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.PARAM_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.PARAM_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.PARAM_GET_URL.getUrl());
        model.addAttribute("paramTypeMap",MAP_PARAM_HASH_TYPE);
        return  "param/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String paramDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        paramService.delete(id);
        return  "redirect:"+ UrlEnum.PARAM_LIST_URL.getUrl()+pageSize+"/"+currNo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String paramAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("参数信息维护","新增参数","新增参数,设置参数信息.",model);
        model.addAttribute("saveUrl", UrlEnum.PARAM_ADD_SAVE_URL.getUrl());
        model.addAttribute("paramCodeValidateUrl", UrlEnum.PARAM_PARAMCODE_VALIDATION_URL.getUrl());
        model.addAttribute("paramTypeMap",Globals.MAP_PARAM_LINKED_TYPE);
        return  "param/add";
    }

    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String paramEditSave(HttpServletRequest request, Param param) throws IOException {
        paramService.save(param);
        return  "redirect:"+ UrlEnum.PARAM_LIST_URL.getUrl()+"10/1";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String paramValidation(Model model, HttpServletRequest request,@PathVariable("column") String column){
        if(column.equals("paramCode")){
            String paramCode = request.getParameter("paramCode");
            Param param = new Param();
            param.setParamCode(paramCode);
            Page<?> page = PageHelper.startPage(1,-1);
            paramMapper.select(param);
            if(page.getTotal() > 0 ){
                return "false";
            }else {
                return "true";
            }
        }
        return  "false";
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String paramEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        Param param = paramService.getByID(id);
        modifyModel("参数信息维护","修改参数信息","管理员对参数信息进行修改操作.",model);
        model.addAttribute("entity",param);
        model.addAttribute("editUrl", UrlEnum.PARAM_EDIT_SAVE_URL.getUrl());
        model.addAttribute("paramTypeMap",MAP_PARAM_LINKED_TYPE);
        return  "param/edit";
    }


    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String paramEditCurrent(Model model, HttpServletRequest request, Param entity  ) throws IOException {
        paramService.update(entity);
        return  "redirect:"+ UrlEnum.PARAM_LIST_URL.getUrl()+"10/1";
    }

}
