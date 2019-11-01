package com.example.project.service;

import com.example.project.mapper.FriendLinkMapper;
import com.example.project.model.FriendLink;
import com.example.project.model.FriendLinkExample;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Service
public class FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    public List<FriendLink> select(Integer page,Integer rows){
        FriendLinkExample example=new FriendLinkExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" create_date desc");
        return friendLinkMapper.selectByExample(example);
    }

    public Integer count(){
        return friendLinkMapper.countByExample(null);
    }

    @Transactional
    public void insert(FriendLink friendLink)throws Exception{
        friendLinkMapper.insert(friendLink);
    }

    @Transactional
    public void edit(FriendLink friendLink)throws Exception{
        friendLinkMapper.updateByPrimaryKeySelective(friendLink);
    }

    @Transactional
    public void delete(String id)throws Exception{
        friendLinkMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void delete(String[] ids)throws Exception{
        for(String id:ids){
            friendLinkMapper.deleteByPrimaryKey(id);
        }
    }

}
