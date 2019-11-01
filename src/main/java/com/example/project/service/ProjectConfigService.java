package com.example.project.service;

import com.example.project.mapper.ProjectConfigMapper;
import com.example.project.model.FriendLink;
import com.example.project.model.ProjectConfig;
import com.example.project.model.ProjectConfigExample;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Service
public class ProjectConfigService {

    @Autowired
    private ProjectConfigMapper projectConfigMapper;

    public List<ProjectConfig> select(Integer page,Integer rows){
        ProjectConfigExample example=new ProjectConfigExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" create_date desc");
        return projectConfigMapper.selectByExample(example);
    }

    public Integer count(){
        return projectConfigMapper.countByExample(null);
    }

    @Transactional
    public void insert(ProjectConfig projectConfig)throws Exception{
        projectConfigMapper.insert(projectConfig);
    }

    @Transactional
    public void edit(ProjectConfig projectConfig)throws Exception{
        projectConfigMapper.updateByPrimaryKeySelective(projectConfig);
    }

    @Transactional
    public void delete(String id)throws Exception{
        projectConfigMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void delete(String[] ids)throws Exception{
        for(String id:ids){
            projectConfigMapper.deleteByPrimaryKey(id);
        }
    }
}
