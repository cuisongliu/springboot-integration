package com.xinyuewulian.admin.controller;

import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    /**
     * 默认参数
     * @param model
     */
    protected void beforeInitModel(Model model){
        model.addAttribute("indexTitle","心悦物联后台管理系统");
        model.addAttribute("indexShortTitle","后台管理系统");
    }

    /**
     * 根据角色判断参数
     * @param model
     * @param userType
     */
    protected void initModel(Model model,Integer userType){
        model.addAttribute("menu",userType==Globals.USER_TYPE_ADMIN?"admin-menu":"user-menu");
        if (userType.equals(Globals.USER_TYPE_ADMIN)){
            beforeInitModel(model);
        }else {
            model.addAttribute("indexTitle","心悦物联业务管理系统");
            model.addAttribute("indexShortTitle","业务管理系统");
        }

    }

    /**
     * 修改模块信息
     * @param title
     * @param name
     * @param decrypt
     * @param model
     */
    protected void modifyModel(String title,String name,String decrypt,Model model){
        model.addAttribute("moduleName1",title);
        model.addAttribute("moduleName2",name);
        model.addAttribute("moduleDecrypt",decrypt);
    }

    /**
     * 获取session
     * @param request
     * @return
     */
    protected HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }


    /**
     * 获取登陆信息
     * @param request
     * @return
     */
    protected LoginInfo getLoginInfo(HttpServletRequest request){
        return (LoginInfo) getSession(request).getAttribute(Globals.SESSION_KEY);
    }

    /**
     * 设置登录信息
     * @param request
     * @return
     */
    protected void setLoginInfo(HttpServletRequest request,LoginInfo loginInfo){
        getSession(request).setAttribute(Globals.SESSION_KEY,loginInfo);
    }
}
