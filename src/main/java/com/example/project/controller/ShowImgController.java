package com.example.project.controller;

import com.example.project.controller.BaseController;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Paths;

/**
 * Created by sw on 2018/4/16 0016.
 */
@Controller
@RequestMapping("/upload")
public class ShowImgController extends BaseController {

    private final ResourceLoader resourceLoader;

    public ShowImgController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 显示图片的方法关键 匹配路径像 localhost:8080/upload/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(path, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
