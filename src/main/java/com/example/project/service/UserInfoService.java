package com.example.project.service;

import com.example.project.mapper.UploadFileMapper;
import com.example.project.mapper.UserInfoMapper;
import com.example.project.model.UploadFile;
import com.example.project.model.UserInfo;
import com.example.project.model.UserInfoExample;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sw on 2017/8/1.
 */
@Service("userService")
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    /**
     * 获取详情
     * @param id
     * @return
     */
    public UserInfo selectDetialById(String id){
        return userInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取详情
     * @param userName
     * @return
     */
    public UserInfo selectDetialByName(String userName) throws Exception{
        UserInfoExample example=new UserInfoExample();
        UserInfoExample.Criteria criteria=example.createCriteria();
        criteria.andLoginNameEqualTo(userName);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if(list!=null&&!list.isEmpty()){
            return  list.get(0);
        }else {
            return null;
        }
    }

    /**
     * 修改
     * @param userInfo
     * @throws Exception
     */
    @Transactional
    public void modifyUserInfo(UserInfo userInfo)throws Exception{
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @Transactional
    public void insertUserInfo(UserInfo user)throws Exception{
        userInfoMapper.insertSelective(user);
    }

    /**
     * 分页查询用户
     * @param page
     * @param rows
     * @return
     */
    public List<UserInfo> selectUserInfoList(Integer page, Integer rows) throws Exception{
        UserInfoExample example=new UserInfoExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
//        example.setOrderByClause(" reg_time desc");
        return userInfoMapper.selectByExample(example);
    }

    /**
     * 用户total
     * @return
     */
    public Integer selectUserInfoTotal() throws Exception{
        return userInfoMapper.countByExample(null);
    }

    /**
     * 删除用户
     * @param id
     * @throws Exception
     */
    @Transactional
    public void deleteUserInfo(String id)throws Exception{
        UploadFile uploadFile =	uploadFileMapper.selectByPrimaryKey(selectDetialById(id).getImgId());
        if(!Util.isNullOrEmpty(uploadFile)){

            uploadFileMapper.deleteByPrimaryKey(selectDetialById(id).getImgId());
        }
        userInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除用户
     * @param ids
     * @throws Exception
     */
    @Transactional
    public void deleteMoreUserInfo(String[] ids)throws Exception{
        for(String id:ids){
            UploadFile uploadFile =	uploadFileMapper.selectByPrimaryKey(selectDetialById(id).getImgId());
            if(!Util.isNullOrEmpty(uploadFile)){
                uploadFileMapper.deleteByPrimaryKey(selectDetialById(id).getImgId());
            }
            userInfoMapper.deleteByPrimaryKey(id);
        }
    }
}
