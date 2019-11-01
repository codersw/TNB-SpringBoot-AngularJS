package com.example.project.service;

import com.example.project.mapper.ArticleClassMapper;
import com.example.project.mapper.ArticleInfoMapper;
import com.example.project.mapper.UploadFileMapper;
import com.example.project.model.*;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sw on 2017/10/25.
 */
@Service("articleService")
public class ArticleService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private ArticleClassMapper articleClassMapper;

    @Autowired
    private UploadFileMapper uploadFileMapper;


    /**
     * 获取种类总条数
     * @param className
     * @param dataType
     * @return
     */
    public Integer selectArticleClassTotal(String className,String dataType) {
        ArticleClassExample example=new ArticleClassExample();
        ArticleClassExample.Criteria criteria= example.createCriteria();
        if(!Util.isNullOrEmpty(className)){
            criteria.andClassNameLike(className);
        }
        criteria.andDataTypeEqualTo(dataType);
        example.or(criteria);
        return articleClassMapper.countByExample(example);
    }

    /**
     * 分页查询种类
     * @param className
     * @param dataType
     * @param page
     * @param rows
     * @return
     */
    public List<ArticleClass> selectArticleClassList(String className,String dataType,Integer page, Integer rows) {
        ArticleClassExample example=new ArticleClassExample();
        ArticleClassExample.Criteria criteria= example.createCriteria();
        if(!Util.isNullOrEmpty(className)){
            criteria.andClassNameLike(className);
        }
        criteria.andDataTypeEqualTo(dataType);
        example.or(criteria);
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" class_short");
        return articleClassMapper.selectByExample(example);
    }

    /**
     * 添加分类信息
     * @param articleClass
     * @throws Exception
     */
    @Transactional
    public void insertArticleClass(ArticleClass articleClass)throws Exception{
        articleClassMapper.insert(articleClass);
    }

    /**
     * 修改分类信息
     * @param articleClass
     * @throws Exception
     */
    @Transactional
    public void editArticleClass(ArticleClass articleClass)throws Exception{
        articleClassMapper.updateByPrimaryKeySelective(articleClass);
    }

    /**
     * 删除分类信息
     * @param id
     * @throws Exception
     */
    @Transactional
    public void deleteArticleClass(String id)throws Exception{
        articleClassMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除分类信息
     * @param ids
     */
    @Transactional
    public void deleteMoreArticleClass(String[] ids)throws Exception{
        ArticleClassExample example=new ArticleClassExample();
        ArticleClassExample.Criteria criteria= example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        example.or(criteria);
        articleClassMapper.deleteByExample(example);
    }

    /**
     * 分页查询文章
     * @param articleTitle
     * @param dataType
     * @param classId
     * @param page
     * @param rows
     * @return
     */
    public List<ArticleInfo> selectArticleInfoList(String articleTitle, String classId, String dataType,Integer page, Integer rows){
        ArticleInfoExample example=new ArticleInfoExample();
        ArticleInfoExample.Criteria criteria=example.createCriteria();
        if(!Util.isNullOrEmpty(articleTitle)){
            criteria.andTitleLike("%"+articleTitle+"%");
        }
        if(!Util.isNullOrEmpty(classId)){
            criteria.andClassIdEqualTo(classId);
        }
        criteria.andDataTypeEqualTo(dataType);
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" is_top desc,create_date desc");
        example.or(criteria);
        return articleInfoMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 文章total
     * @param articleTitle
     * @param classId
     * @param dataType
     * @return
     */
    public Integer selectArticleInfoTotal(String articleTitle,String classId,String dataType){
        ArticleInfoExample example=new ArticleInfoExample();
        ArticleInfoExample.Criteria criteria=example.createCriteria();
        if(!Util.isNullOrEmpty(articleTitle)){
            criteria.andTitleLike("%"+articleTitle+"%");
        }
        if(!Util.isNullOrEmpty(classId)){
            criteria.andClassIdEqualTo(classId);
        }
        criteria.andDataTypeEqualTo(dataType);
        return articleInfoMapper.countByExample(example);
    }

    /**
     * 添加文章
     * @param articleInfo
     * @throws Exception
     */
    @Transactional
    public void insertArticleInfo(ArticleInfo articleInfo)throws Exception{
        articleInfoMapper.insert(articleInfo);
    }

    /**
     * 修改文章
     * @param articleInfo
     * @throws Exception
     */
    @Transactional
    public void editArticleInfo(ArticleInfo articleInfo)throws Exception{
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
    }

    /**
     * 删除文章
     * @param id
     * @throws Exception
     */
    @Transactional
    public void deleteArticleInfo(String id)throws Exception{
        UploadFile uploadFile =	uploadFileMapper.selectByPrimaryKey(selectArticleInfoDetail(id).getImgId());
        if(!Util.isNullOrEmpty(uploadFile)){
            uploadFileMapper.deleteByPrimaryKey(selectArticleInfoDetail(id).getImgId());
        }
        articleInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除文章
     * @param ids
     * @throws Exception
     */
    @Transactional
    public void deleteMoreArticleInfo(String[] ids)throws Exception{
        for(String id:ids){
            UploadFile uploadFile =	uploadFileMapper.selectByPrimaryKey(selectArticleInfoDetail(id).getImgId());
            if(!Util.isNullOrEmpty(uploadFile)){
                uploadFileMapper.deleteByPrimaryKey(selectArticleInfoDetail(id).getImgId());
            }
            articleInfoMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 查询详情
     * @param id
     * @return
     * @throws Exception
     */
    public ArticleInfo selectArticleInfoDetail(String id) {
        return articleInfoMapper.selectByPrimaryKey(id);
    }
}
