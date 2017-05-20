package com.xinyuewulian.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.admin.extra.Utils;
import com.xinyuewulian.bo.UserBo;
import com.xinyuewulian.entity.User;
import com.xinyuewulian.mapper.*;
import com.xinyuewulian.service.UserService;
import com.xinyuewulian.vo.UserVo;
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
@RequestMapping(value = "/user")
public class UserController extends BaseController implements Globals {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userMyEdit(Model model, HttpServletRequest request) throws IOException {
        LoginInfo loginInfo = getLoginInfo(request);
        modifyModel("首页","帐号设置","对个人信息进行修改,可修改项为用户名、密码",model);
        model.addAttribute("entity",loginInfo);
        model.addAttribute("profile","true");
        model.addAttribute("editUrl", UrlEnum.USER_PROFILE_SAVE_URL.getUrl());
        return  "user/edit";
    }

    @RequestMapping(value = "/save/profile",method = RequestMethod.POST)
    public String userEdit(HttpServletRequest request, User user) throws IOException {
        //自己修改
        LoginInfo loginInfo = getLoginInfo(request);
        user.setId(loginInfo.getId());
        user.setLoginPasswd(Utils.getPassword(user.getLoginPasswd()));
        userService.update(user);
        loginInfo.setUserName(user.getUserName());
        setLoginInfo(request,loginInfo);
        return  "redirect:"+ UrlEnum.CONTENT_URL.getUrl()+"10/1";
    }


    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String userList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) throws IOException {
        String keyWords = request.getParameter("keyWords");
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        UserVo userVo = new UserVo();
        userVo.setKeyWords(keyWords);
        userVo.setUserType0(MAP_USER_HASH_TYPE.get(USER_TYPE_ADMIN));
        userVo.setUserType1(MAP_USER_HASH_TYPE.get(USER_TYPE_USER));
        Page<?> page = PageHelper.startPage(currNo,pageSize);
//        PageHelper.orderBy("id desc");
        modifyModel("用户管理","用户列表","显示所有的用户信息.",model);
        List<UserBo> list  =  userMapper.queryUserList(userVo);
        PageInfo<UserBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("loginInfo",getLoginInfo(request));
        model.addAttribute("contentUrl", UrlEnum.USER_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.USER_DEL_URL.getUrl());
        model.addAttribute("addUrl", UrlEnum.USER_ADD_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.USER_GET_URL.getUrl());
        model.addAttribute("userTypeMap",MAP_USER_HASH_TYPE);
        return  "user/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String userDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id) throws IOException {
        userService.delete(id);
        return  userList(model,request,pageSize,currNo);
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String userAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("用户管理","新增用户","新增用户,设置用户名和登录名称.",model);
        model.addAttribute("saveUrl", UrlEnum.USER_ADD_SAVE_URL.getUrl());
        model.addAttribute("loginNameValidateUrl", UrlEnum.USER_LOGINNAME_VALIDATION_URL.getUrl());
        model.addAttribute("userTypeMap", Globals.MAP_USER_LINKED_TYPE);
        return  "user/add";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String userValidation(Model model, HttpServletRequest request,@PathVariable("column") String column) throws IOException {
        if(column.equals("loginName")){
            String loginName = request.getParameter("loginName");
            User u = new User();
            u.setLoginName(loginName);
            Page<?> page = PageHelper.startPage(1,-1);
            userMapper.select(u);
            if(page.getTotal() > 0 ){
                return "false";
            }else {
                return "true";
            }

        }
        return  "false";
    }


    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String userEditSave(HttpServletRequest request, User user) throws IOException {
        user.setLoginPasswd(Utils.getPassword(user.getLoginName()+"123"));
        userService.save(user);
        return  "redirect:"+ UrlEnum.USER_LIST_URL.getUrl()+"10/1";
    }


    public void initModel(Model model,Integer id){
        model.addAttribute("phoneList",phoneMapper.selectAll());
        model.addAttribute("storeList",storeMapper.selectAll());
//        PageHelper.orderBy("type_order");
        model.addAttribute("typeList",typeMapper.queryTypeListByModify(id));
    }
    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String userEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) throws IOException {
        User user = userService.getByID(id);
        modifyModel("用户管理","修改用户信息","管理员对个人信息进行修改,可重置密码、设置手持机等敏感操作.",model);
        model.addAttribute("profile","false");
        model.addAttribute("entity",user);
        initModel(model,id);
        model.addAttribute("editUrl", UrlEnum.USER_EDIT_SAVE_URL.getUrl());
        return  "user/edit";
    }

    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String userEditCurrent(Model model,HttpServletRequest request,UserBo user,String resetPasswd) throws IOException {
        User u = userMapper.selectByPrimaryKey(user.getId());
        if(null != resetPasswd){
            user.setLoginPasswd(Utils.getPassword(u.getLoginName()+"123"));
        }
        userService.modifyUser(user);
        return  userList(model,request,10,1);
    }

}
