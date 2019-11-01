package com.example.project.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public class UploadUtil {

    /**
     * 文件上传
     * @param file
     * @param uploadpath
     * @return
     * @throws Exception
     */
    public static String save(MultipartFile file,String uploadpath) throws Exception {
        String fileName = file.getOriginalFilename();
        String uuid= Util.UUID();
        //创建输出文件对象
        File outFile = new File(uploadpath  +uuid+ getFileType(fileName));
        //拷贝文件到输出文件对象
        FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
        return "/upload/" +uuid + getFileType(fileName);
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    private static String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
