package com.example.project.controller.admin;

import com.example.project.controller.BaseController;
import com.example.project.model.common.DTO;
import com.example.project.service.LeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Controller
@RequestMapping("/admin/leaveword")
public class LeaveWordController extends BaseController{

    @Autowired
    private LeaveWordService leaveWordService;

    /**
     * 跳页
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String page( @PathVariable("page") String page){
        return "/admin/leaveword/"+page;
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
            dto.setDataRows(leaveWordService.select(page,rows));
            dto.setTotal(leaveWordService.count());
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
            leaveWordService.delete(id);
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
            leaveWordService.delete(ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }
}
