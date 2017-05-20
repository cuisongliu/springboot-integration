package com.xinyuewulian.admin.controller;

import com.cuisongliu.springboot.core.util.StringUtil;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.admin.extra.Utils;
import com.xinyuewulian.entity.User;
import com.xinyuewulian.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PortalController extends BaseController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/portal/login",method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest request) throws IOException {
        model.addAttribute("loginUrl", UrlEnum.LOGIN_URL.getUrl());
        HttpSession session=request.getSession();
        String errMsg = (String) session.getAttribute(Globals.ERROR_MSG);
        if(StringUtil.isNotEmptyIgnoreBlank(errMsg)){
            session.removeAttribute(Globals.ERROR_MSG);
            model.addAttribute("errMsg",errMsg);
        }
        beforeInitModel(model);
        return "login";
    }

    @RequestMapping(value = "/portal/login_out",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) throws IOException {
        request.getSession().removeAttribute(Globals.SESSION_KEY);
        return "redirect:"+ UrlEnum.LOGIN_PAGE_URL.getUrl();
    }

    @RequestMapping(value = "/portal/hash_login",method = RequestMethod.POST)
    public String hashLogin(Model model,String loginName,String loginPasswd, HttpServletRequest request) throws IOException {
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPasswd(Utils.getPassword(loginPasswd));
        user = userMapper.selectOne(user);
        if(user!=null){
            LoginInfo loginInfo  = new LoginInfo();
            loginInfo.setId(user.getId());
            loginInfo.setUserName(user.getUserName());
            loginInfo.setUserType(user.getUserType());
            request.getSession().setAttribute(Globals.SESSION_KEY,loginInfo);
            return "redirect:"+ UrlEnum.INDEX_MAIN_URL.getUrl();
        }else {
            request.getSession().setAttribute(Globals.ERROR_MSG,"帐号与密码不匹配,请重新登录.");
            return "redirect:"+ UrlEnum.LOGIN_PAGE_URL.getUrl();
        }
    }
}
