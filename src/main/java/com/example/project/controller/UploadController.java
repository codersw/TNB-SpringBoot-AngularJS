package com.example.project.controller;

import com.example.project.service.UploadFileService;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * Created by sw on 2017/11/21.
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("/img")
    @ResponseBody
    public String uploadImg(MultipartFile file){
        try {
            if(!Util.isNullOrEmpty(file)){
                // 文件类型限制
                String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
                boolean allowed = Arrays.asList(allowedType).contains(file.getContentType());
                if (!allowed) {
                    return "error|不支持的类型";
                }
                // 图片大小限制
                if (file.getSize() > 5 * 1024 * 1024) {
                    return "error|图片大小不能超过5M";
                }
                //返回图片url
                return ctxpath+uploadFileService.addUploadFile(file,path).getFilePath();
            }else{
                return "error|上传失败";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error|上传失败";
        }
    }

}
