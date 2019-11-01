package com.example.project.service;

import com.example.project.mapper.AboutUsMapper;
import com.example.project.model.AboutUs;
import com.example.project.model.AboutUsExample;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Service
public class AboutUsService {

    @Autowired
    private AboutUsMapper aboutUsMapper;

    public List<AboutUs> selectAboutUsList(Integer page,Integer rows){
        AboutUsExample example=new AboutUsExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" create_date desc");
        return aboutUsMapper.selectByExampleWithBLOBs(example);
    }

    public Integer selectAboutUsCount(){
        return aboutUsMapper.countByExample(null);
    }

    @Transactional
    public void insert(AboutUs aboutUs)throws Exception{
        aboutUsMapper.insert(aboutUs);
    }

    @Transactional
    public void edit(AboutUs aboutUs)throws Exception{
        aboutUsMapper.updateByPrimaryKeySelective(aboutUs);
    }

    @Transactional
    public void delete(String uuid)throws Exception{
        aboutUsMapper.deleteByPrimaryKey(uuid);
    }

    @Transactional
    public void delete(String[] ids)throws Exception{
        for(String id:ids){
            aboutUsMapper.deleteByPrimaryKey(id);
        }
    }
}
