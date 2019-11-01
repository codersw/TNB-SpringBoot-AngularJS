package com.example.project.Interceptor;


import com.example.project.Constants;
import com.example.project.model.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sw on 2017/8/24.
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.error("------preHandle------"+request.getServletPath());
        Object obj = (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
        if (obj == null||"".equals(obj.toString())) {
            if (isAjaxRequest(request)) {
                response.setHeader("sessionstatus", "timeout");//ajax 返回状态超时
            } else {
                logger.error("------:------登录失效跳转到login页面！");
                response.sendRedirect(request.getContextPath() +"/admin/login");
            }
            return false;
        }
        return true;
    }

    /**
     * ajax 异步请求的处理
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
