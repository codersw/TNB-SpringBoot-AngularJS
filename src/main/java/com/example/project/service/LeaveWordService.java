package com.example.project.service;

import com.example.project.mapper.LeaveWordMapper;
import com.example.project.model.LeaveWord;
import com.example.project.model.LeaveWordExample;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Service
public class LeaveWordService {

    @Autowired
    private LeaveWordMapper leaveWordMapper;

    public List<LeaveWord> select(Integer page,Integer rows){
        LeaveWordExample example=new LeaveWordExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" create_date desc");
        return leaveWordMapper.selectByExample(example);
    }

    public Integer count(){
        return leaveWordMapper.countByExample(null);
    }

    @Transactional
    public void delete(String id)throws Exception{
        leaveWordMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void delete(String[] ids)throws Exception{
        for(String id:ids){
            leaveWordMapper.deleteByPrimaryKey(id);
        }
    }

    @Transactional
    public void insert(LeaveWord leaveWord)throws Exception{
        leaveWordMapper.insert(leaveWord);
    }
}
