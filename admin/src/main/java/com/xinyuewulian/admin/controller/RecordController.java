package com.xinyuewulian.admin.controller;

import com.cuisongliu.springboot.core.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import com.xinyuewulian.admin.extra.UrlEnum;
import com.xinyuewulian.bo.RecordBo;
import com.xinyuewulian.entity.Record;
import com.xinyuewulian.mapper.RecordMapper;
import com.xinyuewulian.mapper.StoreMapper;
import com.xinyuewulian.mapper.TypeMapper;
import com.xinyuewulian.mapper.UserMapper;
import com.xinyuewulian.service.RecordService;
import com.xinyuewulian.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/record")
public class RecordController extends BaseController implements Globals {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RecordService recordService;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StoreMapper storeMapper;
    @RequestMapping(value = "/list/{pageSize}/{currNo}")
    public String recordList(Model model, HttpServletRequest request,
                           @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        String keyWords = request.getParameter("keyWords");
        RecordVo vo = new RecordVo();
        vo.setKeyWords(keyWords);
        if(null ==pageSize){
            pageSize = 10;
        }
        if(null ==currNo){
            currNo = 1;
        }
        Page<?> page = PageHelper.startPage(currNo,pageSize);
        OrderByHelper.orderBy("s.store_name desc , t.record_date desc,id desc");
        LoginInfo loginInfo = null;
        loginInfo = (LoginInfo) request.getSession().getAttribute(Globals.SESSION_KEY);
        if(loginInfo.getUserType().equals(Globals.USER_TYPE_USER)){
            vo.setCreateUser(loginInfo.getId().longValue());
        }
        modifyModel("货物流水管理","货物流水列表","显示所有的货物流水信息.",model);
        List<RecordBo> list  = recordMapper.queryRecordList(vo);
        PageInfo<RecordBo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("contentUrl", UrlEnum.RECORD_LIST_URL.getUrl());
        model.addAttribute("delUrl", UrlEnum.RECORD_DEL_URL.getUrl());
        model.addAttribute("getUrl", UrlEnum.RECORD_GET_URL.getUrl());
        return  "record/list";
    }

    @RequestMapping(value = "/del/{id}/{pageSize}/{currNo}",method = RequestMethod.POST)
    public String recordDel(Model model, HttpServletRequest request,
                          @PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo,@PathVariable("id") Integer id){
        recordService.delete(id);
        return  "redirect:"+ UrlEnum.RECORD_LIST_URL.getUrl()+pageSize+"/"+currNo;
    }

    public void initModel(HttpServletRequest request,Model model){
        LoginInfo loginInfo = null;
        loginInfo = (LoginInfo) request.getSession().getAttribute(Globals.SESSION_KEY);
        OrderByHelper.orderBy("type_order");
        if(loginInfo.getUserType().equals(Globals.USER_TYPE_USER)){
            model.addAttribute("typeList",typeMapper.queryTypeByUserId(loginInfo.getId()));
            model.addAttribute("storeList",storeMapper.queryStoreByUserId(loginInfo.getId()));
        }else {
            model.addAttribute("typeList",typeMapper.selectAll());
            model.addAttribute("storeList",storeMapper.selectAll());
        }
        model.addAttribute("userList",userMapper.selectAll());
    }


    @RequestMapping(value = "/get/{id}/{pageSize}/{currNo}",method = RequestMethod.GET)
    public String recordEdit(Model model, HttpServletRequest request,@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize, @PathVariable("currNo") Integer currNo) {
        Record entity = recordService.getByID(id);
        modifyModel("货物流水管理","修改参数信息","管理员和用户对货物流水进行二次修改操作.",model);
        model.addAttribute("entity",entity);
        initModel(request,model);
        model.addAttribute("editUrl", UrlEnum.RECORD_EDIT_SAVE_URL.getUrl());
        return  "record/edit";
    }


    @RequestMapping(value = "/save/edit",method = RequestMethod.POST)
    public String recordEditCurrent(Model model, HttpServletRequest request,Record entity ,String createDate ) throws IOException {
        Record r  = recordService.getByID(entity.getId());
        r.setRecordStore(entity.getRecordStore());
        r.setRecordCompany(entity.getRecordCompany());
        r.setRecordType(entity.getRecordType());
        Date recordDate = DateUtil.String2Date(createDate,DateUtil.DEFAULT_TIME_FMT2);
        r.setRecordOperator(entity.getRecordOperator());
        r.setRecordAddress(entity.getRecordAddress());
        r.setRecordDate(recordDate);
        recordService.updateEntity(r);
        return  "redirect:"+ UrlEnum.RECORD_LIST_URL.getUrl()+"10/1";
    }

}
