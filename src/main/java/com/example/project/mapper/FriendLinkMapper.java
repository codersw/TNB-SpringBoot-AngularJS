package com.example.project.mapper;

import com.example.project.model.FriendLink;
import com.example.project.model.FriendLinkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FriendLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int countByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int deleteByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int insert(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int insertSelective(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    List<FriendLink> selectByExample(FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    FriendLink selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int updateByExampleSelective(@Param("record") FriendLink record, @Param("example") FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int updateByExample(@Param("record") FriendLink record, @Param("example") FriendLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int updateByPrimaryKeySelective(FriendLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friend_link
     *
     * @mbggenerated Wed Apr 18 00:01:35 CST 2018
     */
    int updateByPrimaryKey(FriendLink record);
}