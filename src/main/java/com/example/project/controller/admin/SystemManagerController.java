package com.example.project.controller.admin;


import com.example.project.controller.BaseController;
import com.example.project.model.Menu;
import com.example.project.model.common.DTO;
import com.example.project.service.RoleService;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by sw on 2017/11/30.
 */
@Controller
@RequestMapping("/admin/system")
public class SystemManagerController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 跳页
     * @return
     */
    @RequestMapping(value = "/{page}",method = { RequestMethod.GET})
    public String selectRoles(@PathVariable("page") String page,Map<String, Object> map){
        try {
            if(page.equals("roleMenu")){
                map.put("roles",roleService.selectRoleList(null,null));
            }else if(page.equals("sysMenu")){
                map.put("menus",roleService.selectMenuListByRoleId(""));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "/admin/system/"+page;
    }

    /**
     * 获取菜单集合
     * @param roleId
     * @return
     */
    @RequestMapping("/selectMenu")
    @ResponseBody
    public DTO selectMenu(String roleId){
        DTO dto=new DTO();
        try {
            dto.setDataRows(roleService.selectMenuListByRoleId(roleId));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 保存角色菜单
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    @ResponseBody
    public DTO saveRoleMenu(@RequestParam(value="roleId",required = true) String roleId,@RequestParam(value="ids",required = true) String[] ids){
        DTO dto=new DTO();
        try{
            roleService.insertRoleMenu(roleId,ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @RequestMapping("/insertMenu")
    @ResponseBody
    public DTO insertMenu(Menu menu){
        DTO dto=new DTO();
        try{
            menu.setId(Util.UUID());
            roleService.insertMenu(menu);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @RequestMapping("/editMenu")
    @ResponseBody
    public DTO editMenu(Menu menu){
        DTO dto=new DTO();
        try{
            roleService.editMenu(menu);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public DTO deleteMenu(String id){
        DTO dto=new DTO();
        try{
            roleService.deleteMenu(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }

    /**
     * 查询菜单详情
     * @param id
     * @return
     */
    @RequestMapping("/selectMenuDetail")
    @ResponseBody
    public DTO selectMenuDetail(String id){
        DTO dto=new DTO();
        try{
            dto.setData(roleService.selectMenuDetail(id));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
        }
        return dto;
    }
}
