package com.example.project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sw on 2017/11/14.
 */
public abstract class BaseController {

    /**
     * 图片本地路径
     */
    @Value("${web.upload-path}")
    public String path ;

    @Value("${server.context-path}")
    public String ctxpath;
    /**
     * 日志对象
     */
    public Logger logger = LogManager.getLogger(getClass());


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 计算总页数
     * @param total
     * @param rows
     * @return
     */
    public Integer getSumPage(Integer total, Integer rows){
        Integer sumPage = 1;
        if (total > rows) {
            if (total % rows == 0) {
                sumPage = total / rows;
            } else {
                sumPage = (total / rows) + 1;
            }
        }
        return sumPage;
    }
}
