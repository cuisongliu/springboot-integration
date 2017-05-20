package com.xinyuewulian.admin.controller;

import com.cuisongliu.springboot.core.util.DateUtil;
import com.cuisongliu.springboot.core.util.encrypt.BASE64URL;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.StoreBo;
import com.xinyuewulian.entity.Param;
import com.xinyuewulian.entity.Store;
import com.xinyuewulian.mapper.ParamMapper;
import com.xinyuewulian.mapper.StoreMapper;
import com.xinyuewulian.mapper.UserMapper;
import com.xinyuewulian.service.StoreService;
import com.xinyuewulian.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/store")
public class StoreController extends BaseController implements Globals{

    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ParamMapper paramMapper;
    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String storeList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        String keyWords = request.getParameter("keyWords");
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        LoginInfo loginInfo = getLoginInfo(request);
        StoreVo storeVo = new StoreVo();
        storeVo.setKeyWords(keyWords);
        if(loginInfo.getUserType().equals(Globals.USER_TYPE_USER)){
            storeVo.setUserId(loginInfo.getId());
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
//        PageHelper.orderBy("t.store_name desc , t.id desc");
        modifyModel("货物信息维护","货物列表","显示所有的货物信息.",model);

        List<StoreBo> list  = storeMapper.queryStoreList(storeVo);
        PageInfo<StoreBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("contentUrl",UrlEnum.STORE_LIST_URL.getUrl());
        model.addAttribute("qrUrl",UrlEnum.STORE_QR_URL.getUrl());
        model.addAttribute("delUrl",UrlEnum.STORE_DEL_URL.getUrl());
        model.addAttribute("addUrl",UrlEnum.STORE_ADD_URL.getUrl());
        model.addAttribute("getUrl",UrlEnum.STORE_GET_URL.getUrl());
        return  "store/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String storeDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        storeService.delete(id);
        return  "redirect:"+ UrlEnum.STORE_LIST_URL.getUrl()+pageSize+"/"+currNo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String storeAdd(Model model, HttpServletRequest request) throws IOException {
        modifyModel("货物信息维护","新增货物信息","新增货物,设置货物信息.",model);
        model.addAttribute("saveUrl",UrlEnum.STORE_ADD_SAVE_URL.getUrl());
        model.addAttribute("userMap",userMapper.selectAll());
        model.addAttribute("userTypeMap",Globals.MAP_USER_HASH_TYPE);
        model.addAttribute("isUser" ,getLoginInfo(request).getUserType().equals(Globals.USER_TYPE_USER));
        String code = "xywl"+ DateUtil.Date2String(new Date(),"yyyyMMddHHmmss");
        model.addAttribute("code",code);
        return  "store/add";
    }


    @RequestMapping(value = "/save/add",method = RequestMethod.POST)
    public String storeEditSave(HttpServletRequest request, Store store) throws IOException {
        if(getLoginInfo(request).getUserType().equals(Globals.USER_TYPE_USER)){
            store.setUserId(getLoginInfo(request).getId());
        }
        storeService.save(store);
        return  "redirect:"+ UrlEnum.STORE_LIST_URL.getUrl()+"10/1";
    }

    @RequestMapping(value = "/validation/{column}",method = RequestMethod.GET)
    @ResponseBody
    public String storeValidation(Model model, HttpServletRequest request,@PathVariable("column") String column){
        if(column.equals("storeCode")){
        }
        return  "false";
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String storeEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        Store store = storeService.getByID(id);
        modifyModel("货物信息维护","修改货物信息","管理员或者用户对货物信息进行修改操作.",model);
        model.addAttribute("isUser" ,getLoginInfo(request).getUserType().equals(Globals.USER_TYPE_USER));
        model.addAttribute("userMap",userMapper.selectAll());
        model.addAttribute("userTypeMap",Globals.MAP_USER_HASH_TYPE);
        model.addAttribute("entity",store);
        model.addAttribute("editUrl",UrlEnum.STORE_EDIT_SAVE_URL.getUrl());
        return  "store/edit";
    }


    @RequestMapping(value = "/qr/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String storeQrCode(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        Store store = storeService.getByID(id);
        modifyModel("货物信息维护","查看二维码","导出二维码图片.",model);
        String qrCode = BASE64URL.base64UrlEncode(store.getStoreCode().getBytes());
        Param param = new Param();
        param.setParamCode("QR_PREFIX");
        param = paramMapper.selectOne(param);
        model.addAttribute("qrCode",param.getParamValue()+qrCode);
        model.addAttribute("listUrl",UrlEnum.STORE_EDIT_SAVE_URL.getUrl()+pageSize+"/"+currNo);
        return  "store/qr";
    }

    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String storeEditCurrent(Model model, HttpServletRequest request, Store entity  ) throws IOException {
        if(getLoginInfo(request).getUserType().equals(Globals.USER_TYPE_USER)){
            entity.setUserId(getLoginInfo(request).getId());
        }
        storeService.update(entity);
        return  "redirect:"+ UrlEnum.STORE_LIST_URL.getUrl()+"10/1";
    }

}
