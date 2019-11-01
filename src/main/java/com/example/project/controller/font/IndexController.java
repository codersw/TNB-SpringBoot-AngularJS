package com.example.project.controller.font;

import com.example.project.controller.BaseController;
import com.example.project.model.LeaveWord;
import com.example.project.model.common.DTO;
import com.example.project.service.*;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;


/**
 * Created by sw on 2017/9/6.
 */
@Controller
@RequestMapping("/font")
public class IndexController extends BaseController {

    @Autowired
    private ProjectConfigService projectConfigService;

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private LeaveWordService leaveWordService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("aboutUs",aboutUsService.selectAboutUsList(1,1).get(0));
        model.addAttribute("newsList",articleService.selectArticleInfoList("","","1",1,4));
        model.addAttribute("productList",articleService.selectArticleInfoList("","","1",1,4));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        return "font/index";
    }

    /**
     * 关于我们
     * @param model
     * @return
     */
    @RequestMapping(value="/aboutus", method = RequestMethod.GET)
    public String aboutus(Model model){
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("aboutUs",aboutUsService.selectAboutUsList(1,1).get(0));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        return "font/aboutus";
    }

    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping(value="/leaveword", method = RequestMethod.GET)
    public String leaveword(Model model){
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        return "font/leaveword";
    }

    /**
     * 插入留言信息
     * @param request
     * @param leaveWord
     * @param code
     * @return
     */
    //TODO 没有防无限提交校验
    @RequestMapping("/insertLeave")
    @ResponseBody
    public DTO insertLeave(HttpServletRequest request, LeaveWord leaveWord, String code){
        DTO dto=new DTO();
        try{
            HttpSession session=request.getSession(true);
            String sessionCode=(String)session.getAttribute("code");
            if(sessionCode.equalsIgnoreCase(code)){
                leaveWord.setId(Util.UUID());
                leaveWord.setCreateDate(new Date());
                leaveWordService.insert(leaveWord);
            }else {
                dto.setStatus(DTO.ERROR);
                dto.setMsg("验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            dto.setStatus(DTO.ERROR);
            dto.setMsg(e.getMessage());
        }
        return dto;
    }

    /**
     * 新闻集合页
     * @param model
     * @param page
     * @param rows
     * @param classId
     * @return
     */
    @RequestMapping(value="/news", method = RequestMethod.GET)
    public String news(Model model,Integer page,Integer rows,String classId){
        if(Util.isNullOrEmpty(page)){
            page=1;
        }
        if(Util.isNullOrEmpty(rows)){
            rows=8;
        }
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        model.addAttribute("newsList",articleService.selectArticleInfoList("",classId,"1",page,rows));
        model.addAttribute("newsClassList",articleService.selectArticleClassList("","1",null,null));
        model.addAttribute("count",super.getSumPage(articleService.selectArticleInfoTotal("",classId,"1"),rows));
        model.addAttribute("page",page);
        return "font/news";
    }

    /**
     * 产品集合页
     * @param model
     * @param page
     * @param rows
     * @param classId
     * @return
     */
    @RequestMapping(value="/product", method = RequestMethod.GET)
    public String product(Model model,Integer page,Integer rows,String classId){
        if(Util.isNullOrEmpty(page)){
            page=1;
        }
        if(Util.isNullOrEmpty(rows)){
            rows=8;
        }
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        model.addAttribute("productList",articleService.selectArticleInfoList("",classId,"2",page,rows));
        model.addAttribute("productClassList",articleService.selectArticleClassList("","2",null,null));
        model.addAttribute("count",super.getSumPage(articleService.selectArticleInfoTotal("",classId,"2"),rows));
        model.addAttribute("page",page);
        return "font/product";
    }

    /**
     * 详情页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model,@PathVariable("id") String id){
        model.addAttribute("projectConfig",projectConfigService.select(1,1).get(0));
        model.addAttribute("friendLinkList",friendLinkService.select(null,null));
        model.addAttribute("data",articleService.selectArticleInfoDetail(id));
        return "font/detail";
    }
}
