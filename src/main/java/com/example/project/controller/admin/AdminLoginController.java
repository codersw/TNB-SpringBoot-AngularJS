package com.example.project.controller.admin;

import com.example.project.Constants;
import com.example.project.controller.BaseController;
import com.example.project.model.Menu;
import com.example.project.model.UserInfo;
import com.example.project.service.RoleService;
import com.example.project.service.UserInfoService;
import com.example.project.util.Util;
import com.example.project.util.pwdencoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sw on 2017/9/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳页
     * @param page
     * @param map
     * @return
     */
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable("page")String page,Map<String,Object> map){
        logger.info("跳页"+page);
        map.put("message","");
        return "/admin/"+page;
    }

    /**
     * 登录
     * @param map
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Map<String,Object> map, String username, String password, HttpSession session){
        String url="";
        try {
            UserInfo userInfo = userInfoService.selectDetialByName(username);
            if(userInfo!=null){
                if(pwdencoderUtil.encodePassword(password).equals(userInfo.getPassword())){
                    map.put("message","登录成功");
                    session.setAttribute(Constants.SESSION_NAME_LOGIN_MENU.toString(),getMenu(roleService.selectMenuListByRoleId(userInfo.getRoleId())));
                    session.setAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString(),userInfo);
                    url = "redirect:/admin/index.html";
                }else{
                    map.put("message","登录用户密码错误");
                    url= "/admin/login";
                }
            }else{
                map.put("message","登录用户不存在");
                url= "/admin/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            map.put("message","数据操作失败");
            url= "/admin/login";
        }
        return url;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        String url="";
        try{
            session.removeAttribute(Constants.SESSION_NAME_LOGIN_RESULT.toString());
            url= "redirect:/admin/login.html";
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            url= "/admin/index";
        }
        return url;
    }

    /**
     * 获取角色菜单集合
     * @param roleMenus
     * @return
     */
    public List<Menu> getMenu(List<Menu> roleMenus){
        List<Menu> menuList=new ArrayList<Menu>();
        List<Menu> result = new ArrayList<Menu>();
        if(!Util.isNullOrEmpty(roleMenus)){
            for(Menu menu:roleMenus){
                if(!Util.isNullOrEmpty(menu.getParentId())&&menu.getParentId().equals(Constants.SYSTEM_ROOT_MENU_ID.toString())){
                    menuList.add(menu);
                }
            }
        }
        if(!Util.isNullOrEmpty(menuList)){
            for(Menu menu:menuList){
                if(!Util.isNullOrEmpty(menu.getParentId())){
                    menu.setChildMenu(roleService.selectMenuListByPrentId(menu.getId()));
                }
                result.add(menu);
            }
        }
        logger.info(result.size());
        return result;
    }
}
