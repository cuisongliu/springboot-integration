package com.xinyuewulian.admin.interceptor;

import com.xinyuewulian.admin.extra.Globals;
import com.xinyuewulian.admin.extra.LoginInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止用户二次登录,若登录后输入登录网址则跳转到主页
 * @author jerry
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LoginInfo loginInfo = null;
        loginInfo = (LoginInfo) httpServletRequest.getSession().getAttribute(Globals.SESSION_KEY);
        if (loginInfo != null) {
            return true;
        }else{
//            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/portal/no_login");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "认证失败: 用户没有登录,请重新登录." );
            return false;
        }
    }
}
