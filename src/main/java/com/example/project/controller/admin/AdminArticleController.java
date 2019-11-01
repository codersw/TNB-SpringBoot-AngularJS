package com.example.project.controller.admin;

import com.example.project.Constants;
import com.example.project.controller.BaseController;
import com.example.project.model.ArticleClass;
import com.example.project.model.ArticleInfo;
import com.example.project.model.UserInfo;
import com.example.project.model.common.DTO;
import com.example.project.service.ArticleService;
import com.example.project.service.UploadFileService;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 文章管理
 * Created by sw on 2017/10/25.
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 跳页
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String page( @PathVariable("page") String page,Map<String,Object> map){
        try {
            if(page.equals("newsInfo")){
                map.put("articleClassList",articleService.selectArticleClassList(null,"1",null,null));
            }
            if(page.equals("productInfo")){
                map.put("articleClassList",articleService.selectArticleClassList(null,"2",null,null));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "/admin/article/"+page;
    }

    /**
     * 查询分类集合
     * @param page
     * @param rows
     * @param className
     * @param dataType
     * @return
     */
    @RequestMapping(value="/articleClassList",method={RequestMethod.POST})
    @ResponseBody
    public DTO articleClassList(Integer page, Integer rows, String className,String dataType){
        DTO dto=new DTO();
        try{
            dto.setDataRows(articleService.selectArticleClassList(className,dataType,page,rows));
            dto.setTotal(articleService.selectArticleClassTotal(className,dataType));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 插入分类信息
     * @param articleClass
     * @param request
     * @return
     */
    @RequestMapping(value = "/insertArticleClass",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertArticleClass(HttpServletRequest request,ArticleClass articleClass){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            articleClass.setId(Util.UUID());
            articleClass.setCreateUserId(user.getId());
            articleClass.setEditUserId(user.getId());
            articleClass.setCreateDate(new Date());
            articleClass.setEditDate(new Date());
            articleService.insertArticleClass(articleClass);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 修改分类信息
     * @param articleClass
     * @param request
     * @return
     */
    @RequestMapping(value = "/editArticleClass",method = RequestMethod.POST)
    @ResponseBody
    public DTO editArticleClass(HttpServletRequest request,ArticleClass articleClass){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            articleClass.setEditUserId(user.getId());
            articleClass.setEditDate(new Date());
            articleService.editArticleClass(articleClass);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 删除信息
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/deleteArticle",method = RequestMethod.POST)
    @ResponseBody
    public DTO deleteArticle(@RequestParam(value="id",required=true)String id,@RequestParam(value="type",required=true)String type){
        DTO dto=new DTO();
        try{
            if(type.equals("ArticleClass")){
                articleService.deleteArticleClass(id);
            }else if(type.equals("ArticleInfo")){
                articleService.deleteArticleInfo(id);
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
    @RequestMapping(value = "/deleteMoreArticle",method = RequestMethod.POST)
    @ResponseBody
    public DTO deleteMoreArticle(@RequestParam(value="ids",required=true)String[] ids,@RequestParam(value="type",required=true)String type){
        DTO dto=new DTO();
        try{
            if(type.equals("ArticleClass")){
                articleService.deleteMoreArticleClass(ids);
            }else if(type.equals("ArticleInfo")) {
                articleService.deleteMoreArticleInfo(ids);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }


    /**
     * 查询文章集合
     * @param page
     * @param rows
     * @param articleTitle
     * @param classId
     * @param dataType
     * @return
     */
    @RequestMapping(value="/articleInfoList",method={RequestMethod.POST})
    @ResponseBody
    public DTO articleInfoList(Integer page,Integer rows,String articleTitle,String classId,String dataType){
        DTO dto=new DTO();
        try{
            dto.setDataRows(articleService.selectArticleInfoList(articleTitle,classId,dataType,page,rows));
            dto.setTotal(articleService.selectArticleInfoTotal(articleTitle,classId,dataType));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 插入文章信息
     * @param request
     * @param articleInfo
     * @param file
     * @return
     */
    @RequestMapping(value = "/insertArticleInfo",method = RequestMethod.POST)
    @ResponseBody
    public DTO insertArticleClass(HttpServletRequest request, ArticleInfo articleInfo, MultipartFile file){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            articleInfo.setId(Util.UUID());
            articleInfo.setCreateUserId(user.getId());
            articleInfo.setEditUserId(user.getId());
            articleInfo.setCreateDate(new Date());
            articleInfo.setEditDate(new Date());
            if(!Util.isNullOrEmpty(file)){
                articleInfo.setImgId(uploadFileService.addUploadFile(file,path).getId());
            }
            articleService.insertArticleInfo(articleInfo);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 修改文章信息
     * @param request
     * @param articleInfo
     * @param file
     * @return
     */
    @RequestMapping(value = "/editArticleInfo",method = RequestMethod.POST)
    @ResponseBody
    public DTO editArticleInfo(HttpServletRequest request,ArticleInfo articleInfo,MultipartFile file){
        DTO dto=new DTO();
        try{
            UserInfo user= (UserInfo)request.getSession().getAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            articleInfo.setEditUserId(user.getId());
            articleInfo.setEditDate(new Date());
            if(!Util.isNullOrEmpty(file)){
                articleInfo.setImgId(uploadFileService.addUploadFile(file,path).getId());
            }
            articleService.editArticleInfo(articleInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }
}
