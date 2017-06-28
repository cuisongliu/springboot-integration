package com.xinyuewulian.admin.extra;

/**
 * Created by cuisongliu on 2016/11/23.
 *
 * @author cuisongliu
 */
public enum UrlEnum {
    INDEX_URL("/"),
    INDEX_MAIN_URL("/index"),
    LOGOUT_URL("/portal/login_out"),
    LOGIN_PAGE_URL("/portal/login"),
    LOGIN_URL("/portal/hash_login"),

    USER_LIST_URL("/user/list/"),
    USER_DEL_URL("/user/del/"),
    USER_GET_URL("/user/get/"),
    USER_ADD_URL("/user/add"),
    USER_LOGINNAME_VALIDATION_URL("/user/validation/loginName"),
    //profile的保存
    USER_PROFILE_SAVE_URL("/user/save/profile"),
    //edit页面的保存
    USER_EDIT_SAVE_URL("/user/save/edit"),
    USER_ADD_SAVE_URL("/user/save/add"),

    PARAM_LIST_URL("/param/list/"),
    PARAM_DEL_URL("/param/del/"),
    PARAM_GET_URL("/param/get/"),
    PARAM_ADD_URL("/param/add"),
    PARAM_PARAMCODE_VALIDATION_URL("/param/validation/paramCode"),
    //edit页面的保存
    PARAM_EDIT_SAVE_URL("/param/save/edit"),
    PARAM_ADD_SAVE_URL("/param/save/add"),


    PHONE_LIST_URL("/phone/list/"),
    PHONE_DEL_URL("/phone/del/"),
    PHONE_GET_URL("/phone/get/"),
    PHONE_ADD_URL("/phone/add"),
    PHONE_PHONESERIAL_VALIDATION_URL("/phone/validation/phoneSerial"),
    //edit页面的保存
    PHONE_EDIT_SAVE_URL("/phone/save/edit"),
    PHONE_ADD_SAVE_URL("/phone/save/add"),

    PHONE_SELECT_LIST_URL("/phone/select/list/"),
    PHONE_SELECT_DEL_URL("/phone/select/del/"),


    SYSTEM_LIST_URL("/system/list/"),
    SYSTEM_DEL_URL("/system/del/"),
    SYSTEM_GET_URL("/system/get/"),
    SYSTEM_ADD_URL("/system/add"),
    //edit页面的保存
    SYSTEM_EDIT_SAVE_URL("/system/save/edit"),
    SYSTEM_ADD_SAVE_URL("/system/save/add"),



    TYPE_LIST_URL("/type/list/"),
    TYPE_DEL_URL("/type/del/"),
    TYPE_GET_URL("/type/get/"),
    TYPE_ADD_URL("/type/add"),
    TYPE_TYPECODE_VALIDATION_URL("/type/validation/typeCode"),
    //edit页面的保存
    TYPE_EDIT_SAVE_URL("/type/save/edit"),
    TYPE_ADD_SAVE_URL("/type/save/add"),


    RECORD_LIST_URL("/record/list/"),
    RECORD_DEL_URL("/record/del/"),
    RECORD_GET_URL("/record/get/"),
    //edit页面的保存
    RECORD_EDIT_SAVE_URL("/record/save/edit"),

    STORE_LIST_URL("/store/list/"),
    STORE_DEL_URL("/store/del/"),
    STORE_QR_URL("/store/qr/"),
    STORE_GET_URL("/store/get/"),
    STORE_ADD_URL("/store/add"),
//    STORE_STORECODE_VALIDATION_URL("/store/validation/phoneSerial"),
    //edit页面的保存
    STORE_EDIT_SAVE_URL("/store/save/edit"),
    STORE_ADD_SAVE_URL("/store/save/add"),


    SERIAL_TYPE_LIST_URL("/serial/type/list/"),
    SERIAL_TYPE_DEL_URL("/serial/type/del/"),
    SERIAL_TYPE_GET_URL("/serial/type/get/"),
    SERIAL_TYPE_ADD_URL("/serial/type/add"),
    SERIAL_TYPE_CODE_VALIDATION_URL("/serial/type/validation/serialTypeCode"),
    //edit页面的保存
    SERIAL_TYPE_EDIT_SAVE_URL("/serial/type/save/edit"),
    SERIAL_TYPE_ADD_SAVE_URL("/serial/type/save/add"),

    SERIAL_NO_LIST_URL("/serial/no/list/"),


    PROFILE_URL("/user/profile"),
    CONTENT_URL("/content/");




    // 枚举对象的 RSS 地址的属性
    private String url;

    private UrlEnum(String url){
        this.url = url;
    }

    public String getUrl() {
       return url;
    }
}
