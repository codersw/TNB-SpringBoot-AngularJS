package com.example.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sw on 2017/10/18.
 */
@Controller
public class ErrorController extends BaseController{

    @RequestMapping(value="/error/{status}")
    public String handleError(@PathVariable("status") String status){
        return status;
    }
}
