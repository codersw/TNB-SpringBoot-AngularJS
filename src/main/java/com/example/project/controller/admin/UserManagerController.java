package com.example.project.controller.admin;

import com.example.project.Constants;
import com.example.project.controller.BaseController;
import com.example.project.model.Role;
import com.example.project.model.UserInfo;
import com.example.project.model.common.DTO;
import com.example.project.service.RoleService;
import com.example.project.service.UploadFileService;
import com.example.project.service.UserInfoService;
import com.example.project.util.Util;
import com.example.project.util.pwdencoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/admin/user")
public class UserManagerController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 跳页
     * @param page
     * @param map
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String page(@PathVariable("page") String page, Map<String,Object> map) {
        try {
            if(page.equals("userInfo")){
                map.put("userRoleList", roleService.selectRoleList(null,null));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "/admin/user/" + page;
    }

    /**
     * 删除信息
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public DTO deleteUser(@RequestParam(value="id",required=true)String id, @RequestParam(value="type",required=true)String type){
        DTO dto=new DTO();
        try{
            if(type.equals("UserInfo")) {
                userInfoService.deleteUserInfo(id);
            }
            if(type.equals("Role")) {
                roleService.deleteRole(id);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 批量删除信息
     * @param ids
     * @param type
     * @return
     */
    @RequestMapping(value = "/deleteMoreUser",method = RequestMethod.POST)
    @ResponseBody
    public DTO deleteMoreUser(@RequestParam(value="ids",required=true)String[] ids, @RequestParam(value="type",required=true)String type){
        DTO dto=new DTO();
        try{
            if(type.equals("UserInfo")) {
                userInfoService.deleteMoreUserInfo(ids);
            }
            if(type.equals("Role")) {
                roleService.deleteMoreRole(ids);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 用户查询集合
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/userInfoList",method={RequestMethod.POST})
    @ResponseBody
    public DTO userInfoList(Integer page,Integer rows){
        DTO dto=new DTO();
        try{
            dto.setDataRows(userInfoService.selectUserInfoList(page,rows));
            dto.setTotal(userInfoService.selectUserInfoTotal());
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 新增用户
     * @param userInfo
     * @param file
     * @return
     */
    @RequestMapping(value = "/insertUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertUserInfo(HttpServletRequest request,UserInfo userInfo, MultipartFile file){
        DTO dto=new DTO();
        try{
            if (!Util.isNullOrEmpty(userInfoService.selectDetialByName(userInfo.getLoginName()))){
                dto.setStatus(DTO.ERROR);
                dto.setMsg(",登录名已存在！");
            }else {
                userInfo.setId(Util.UUID());
                userInfo.setPassword(pwdencoderUtil.encodePassword(Constants.USER_DEFAULT_PASS_WORD.toString()));
                if(!Util.isNullOrEmpty(file)){
                    userInfo.setImgId(uploadFileService.addUploadFile(file,path).getId());
                }
                userInfoService.insertUserInfo(userInfo);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 修改用户
     * @param userInfo
     * @param file
     * @return
     */
    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public DTO editUserInfo(HttpServletRequest request, UserInfo userInfo, MultipartFile file){
        DTO dto=new DTO();
        try{
            if(!Util.isNullOrEmpty(file)){
                userInfo.setImgId(uploadFileService.addUploadFile(file,path).getId());
            }
            userInfoService.modifyUserInfo(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }


    /**
     * 角色查询集合
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/roleList",method = RequestMethod.POST)
    @ResponseBody
    public DTO roleList(Integer page,Integer rows){
        DTO dto=new DTO();
        try{
            dto.setDataRows(roleService.selectRoleList(page,rows));
            dto.setTotal(roleService.selectRoleCount());
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/insertRole",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertRole(Role role){
        DTO dto=new DTO();
        try{
            role.setId(Util.UUID());
            role.setCreateDate(new Date());
            roleService.insertRole(role);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/editRole",method = RequestMethod.POST)
    @ResponseBody
    public DTO editUserInfo(Role role){
        DTO dto=new DTO();
        try{
            roleService.editRole(role);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }
}
