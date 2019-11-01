package com.example.project.mapper;

import com.example.project.model.ArticleClass;
import com.example.project.model.ArticleClassExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int countByExample(ArticleClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int deleteByExample(ArticleClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int insert(ArticleClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int insertSelective(ArticleClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    List<ArticleClass> selectByExample(ArticleClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    ArticleClass selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int updateByExampleSelective(@Param("record") ArticleClass record, @Param("example") ArticleClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int updateByExample(@Param("record") ArticleClass record, @Param("example") ArticleClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int updateByPrimaryKeySelective(ArticleClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_article_class
     *
     * @mbggenerated Tue Apr 17 22:38:35 CST 2018
     */
    int updateByPrimaryKey(ArticleClass record);
}