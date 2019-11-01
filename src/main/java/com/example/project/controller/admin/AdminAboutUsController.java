package com.example.project.controller.admin;

import com.example.project.Constants;
import com.example.project.controller.BaseController;
import com.example.project.model.AboutUs;
import com.example.project.model.UserInfo;
import com.example.project.model.common.DTO;
import com.example.project.service.AboutUsService;
import com.example.project.service.UploadFileService;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Controller
@RequestMapping("/admin/aboutus")
public class AdminAboutUsController extends BaseController {

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 跳页
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String page( @PathVariable("page") String page){
        return "/admin/aboutus/"+page;
    }

    /**
     * 列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectAboutUsList")
    @ResponseBody
    public DTO selectAboutUsList(Integer page, Integer rows){
        DTO dto=new DTO();
        try{
            dto.setDataRows(aboutUsService.selectAboutUsList(page,rows));
            dto.setTotal(aboutUsService.selectAboutUsCount());
        }catch (Exception e){
            e.printStackTrace();
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 插入
     * @param aboutUs
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertAboutUs(AboutUs aboutUs, HttpServletRequest request, MultipartFile file){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            aboutUs.setId(Util.UUID());
            aboutUs.setCreateDate(new Date());
            aboutUs.setCreateUserId(user.getId());
            aboutUs.setEditUserId(user.getId());
            aboutUs.setEditDate(new Date());
            if(!Util.isNullOrEmpty(file)){
                aboutUs.setImgId(uploadFileService.addUploadFile(file,path).getId());
            }
            aboutUsService.insert(aboutUs);
        }catch (Exception e){
            e.printStackTrace();
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 修改
     * @param aboutUs
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public DTO eidtAboutUs(AboutUs aboutUs, HttpServletRequest request, MultipartFile file){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            aboutUs.setEditUserId(user.getId());
            aboutUs.setEditDate(new Date());
            if(!Util.isNullOrEmpty(file)){
                aboutUs.setImgId(uploadFileService.addUploadFile(file,path).getId());
            }
            aboutUsService.edit(aboutUs);
        }catch (Exception e){
            e.printStackTrace();
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public DTO delete(@RequestParam(value="id",required=true)String id){
        DTO dto=new DTO();
        try{
            aboutUsService.delete(id);
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
     * @return
     */
    @RequestMapping(value = "/deleteMore",method = RequestMethod.POST)
    @ResponseBody
    public DTO deleteMore(@RequestParam(value="ids",required=true)String[] ids){
        DTO dto=new DTO();
        try{
            aboutUsService.delete(ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }
}
