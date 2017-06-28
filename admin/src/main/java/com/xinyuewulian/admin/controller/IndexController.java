package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.RecordBo;
import com.xinyuewulian.mapper.RecordMapper;
import com.xinyuewulian.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private RecordMapper recordMapper;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        LoginInfo loginInfo = null;
        loginInfo = (LoginInfo) request.getSession().getAttribute(Globals.SESSION_KEY);
        if (loginInfo != null) {
            return  "redirect:"+ UrlEnum.INDEX_MAIN_URL.getUrl();
        }else{
            return "redirect:"+ UrlEnum.LOGIN_PAGE_URL.getUrl();
        }
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String defaultView(Model model,HttpServletRequest request) throws IOException {
        LoginInfo loginInfo = (LoginInfo)request.getSession().getAttribute(Globals.SESSION_KEY);
        model.addAttribute("userName",loginInfo.getUserName());
        model.addAttribute("indexUrl", UrlEnum.INDEX_URL.getUrl());
        model.addAttribute("loginOutUrl", UrlEnum.LOGOUT_URL.getUrl());
        model.addAttribute("profileUrl", UrlEnum.PROFILE_URL.getUrl());
        model.addAttribute("contentUrl", UrlEnum.CONTENT_URL.getUrl());
        initModel(model,loginInfo.getUserType());
        initUrl(model,loginInfo.getUserType());
        return  "index";
    }

    private void initUrl(Model model,Integer userType){
        model.addAttribute("recordManagerUrl", UrlEnum.RECORD_LIST_URL.getUrl()+"/10/1");
        model.addAttribute("storeManagerUrl", UrlEnum.STORE_LIST_URL.getUrl()+"/10/1");
        if(userType.equals(Globals.USER_TYPE_ADMIN)){
            model.addAttribute("userManagerUrl", UrlEnum.USER_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("paramManagerUrl", UrlEnum.PARAM_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("phoneManagerUrl", UrlEnum.PHONE_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("phoneSelectManagerUrl", UrlEnum.PHONE_SELECT_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("systemManagerUrl", UrlEnum.SYSTEM_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("typeManagerUrl", UrlEnum.TYPE_LIST_URL.getUrl()+"/10/1");
            model.addAttribute("serialNoManagerUrl", UrlEnum.SERIAL_NO_LIST_URL.getUrl()+"/10/1");
        }
    }

    @RequestMapping(value = "/content/{pageSize}/{currNo}")
    public String content(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) throws IOException {
        LoginInfo loginInfo = null;
        String keyWords = request.getParameter("keyWords");
        loginInfo = (LoginInfo) request.getSession().getAttribute(Globals.SESSION_KEY);
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
        OrderByHelper.orderBy("s.store_name desc , t.record_date desc,t.id desc");
        RecordVo vo = new RecordVo();
        vo.setKeyWords(keyWords);
        List<RecordBo> list = null;
        if(loginInfo.getUserType().equals(Globals.USER_TYPE_ADMIN)){
            modifyModel("首页","","对心悦物联系统进行安卓和web页面进行接口和手持机信息管理,包括用户和流水信息等.",model);
        }else {
            vo.setCreateUser(loginInfo.getId().longValue());
            modifyModel("首页","","心悦物联系统业务人员对自己的货物信息管理和流水统计并且生成相对应的二维码.",model);
        }
        list =  recordMapper.queryRecordList(vo);
        PageInfo<RecordBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("contentUrl", UrlEnum.CONTENT_URL.getUrl());
        return  "content";
    }


}
