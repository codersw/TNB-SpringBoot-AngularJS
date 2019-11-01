package com.example.project.controller.admin;

import com.example.project.Constants;
import com.example.project.controller.BaseController;
import com.example.project.model.FriendLink;
import com.example.project.model.UserInfo;
import com.example.project.model.common.DTO;
import com.example.project.service.FriendLinkService;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Controller
@RequestMapping("/admin/friendlink")
public class FriendLinkController extends BaseController{

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 跳页
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String page( @PathVariable("page") String page){
        return "/admin/friendlink/"+page;
    }
    /**
     * 列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public DTO select(Integer page, Integer rows){
        DTO dto=new DTO();
        try{
            dto.setDataRows(friendLinkService.select(page,rows));
            dto.setTotal(friendLinkService.count());
        }catch (Exception e){
            e.printStackTrace();
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 插入
     * @param friendLink
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertAboutUs(FriendLink friendLink, HttpServletRequest request){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            friendLink.setId(Util.UUID());
            friendLink.setCreateDate(new Date());
            friendLink.setCreateUserId(user.getId());
            friendLink.setEditUserId(user.getId());
            friendLink.setEditDate(new Date());
            friendLinkService.insert(friendLink);
        }catch (Exception e){
            e.printStackTrace();
            dto.setStatus(DTO.ERROR);
            logger.error(e.getMessage());
        }
        return dto;
    }

    /**
     * 修改
     * @param friendLink
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public DTO eidtAboutUs(FriendLink friendLink, HttpServletRequest request){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            friendLink.setEditUserId(user.getId());
            friendLink.setEditDate(new Date());
            friendLinkService.edit(friendLink);
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
            friendLinkService.delete(id);
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
            friendLinkService.delete(ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

}
