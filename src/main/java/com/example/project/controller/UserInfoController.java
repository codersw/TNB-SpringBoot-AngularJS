package com.example.project.controller;

import com.example.project.Constants;
import com.example.project.model.common.DTO;
import com.example.project.model.UserInfo;
import com.example.project.service.UploadFileService;
import com.example.project.service.UserInfoService;
import com.example.project.util.Util;
import com.example.project.util.pwdencoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by sw on 2017/9/6.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 跳页
     * @param map
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String index(Map<String,Object> map, @PathVariable("page") String page){
        map.put("message", "");
        map.put("success", "");
        return "/admin/"+page;
    }

    /**
     * 修改信息
     * @param session
     * @param userInfo
     * @param file
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public DTO modifyUserInfo(HttpSession session, HttpServletRequest request, UserInfo userInfo, MultipartFile file) {
        DTO dto=new DTO();
        try {
            UserInfo sessionUserInfo = (UserInfo) session.getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            if(!Util.isNullOrEmpty(userInfo)){
                if(!Util.isNullOrEmpty(file)){
                    userInfo.setImgId(uploadFileService.addUploadFile(file,path).getId());
                }else{
                    userInfo.setImgId(sessionUserInfo.getImgId());
                }
                userInfoService.modifyUserInfo(userInfo);
                session.setAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString(),userInfoService.selectDetialById(userInfo.getId()));
                dto.setMsg("用户信息修改提交成功");
            }else{
                dto.setMsg("数据操作失败");
                dto.setStatus(DTO.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsg("数据操作失败");
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }


    /**
     * 密码修改
     * @param session
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public DTO modifyPassword(HttpSession session,String oldPassword, String newPassword) {
        DTO dto=new DTO();
        try {
            UserInfo user = (UserInfo)session.getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            if (pwdencoderUtil.encodePassword(oldPassword).equals(user.getPassword())) {
                user.setPassword(pwdencoderUtil.encodePassword(newPassword));
                userInfoService.modifyUserInfo(user);
                dto.setMsg ("登录密码修改成功");
            }else{
                dto.setMsg ( "登录密码输入错误");
                dto.setStatus(DTO.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsg ( "数据操作失败");
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Map<String,Object> map,String username,String password){
        try{
            UserInfo userInfo = userInfoService.selectDetialByName(username);
            if(userInfo!=null){
                UserInfo user=new UserInfo();
                user.setId(Util.UUID());
                user.setLoginName(username);
                user.setPassword(pwdencoderUtil.encodePassword(password));
                userInfoService.insertUserInfo(user);
                map.put("message","创建成功请登录");
            }else{
                map.put("message","已存在该用户");
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "/admin/login";
    }
}
