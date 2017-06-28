package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.entity.SerialNo;
import com.xinyuewulian.mapper.SerialNoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/serial/no")
public class SerialNoController extends BaseController implements Globals{

    @Autowired
    private SerialNoMapper serialNoMapper;

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
        OrderByHelper.orderBy("id desc");
        modifyModel("序列号序列","序列号序列列表","显示所有的序列号序列信息.",model);
        List<SerialNo> list  =  serialNoMapper.selectAll();
        PageInfo<SerialNo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("loginInfo",getLoginInfo(request));
        model.addAttribute("contentUrl", UrlEnum.TYPE_LIST_URL.getUrl());
        return  "serial/no/list";
    }

}
