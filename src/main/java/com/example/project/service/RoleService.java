package com.example.project.service;

import com.example.project.mapper.MenuMapper;
import com.example.project.mapper.RoleMapper;
import com.example.project.mapper.RoleMenuMapper;
import com.example.project.model.*;
import com.example.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sw on 2017/11/29.
 */
@Service
public class RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取权限菜单集合
     * @param roleId
     * @return
     */
    public List<RoleMenu> selectRoleMenuList(String roleId){
        RoleMenuExample example=new RoleMenuExample();
        RoleMenuExample.Criteria criteria=example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        example.or(criteria);
        return roleMenuMapper.selectByExample(example);
    }

    /**
     * 根据权限id查询菜单集合
     * @param roleId
     * @return
     */
    public List<Menu> selectMenuListByRoleId(String roleId){
        if(Util.isNullOrEmpty(roleId)){
            MenuExample example=new MenuExample();
            example.setOrderByClause(" sort");
            return menuMapper.selectByExample(example);
        }else{
            return menuMapper.selectByRoleId(roleId);
        }
    }

    /**
     * 根据父级菜单id查询菜单集合
     * @param parentId
     * @return
     */
    public List<Menu> selectMenuListByPrentId(String parentId){
        MenuExample example=new MenuExample();
        MenuExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        example.or(criteria);
        example.setOrderByClause(" sort");
        return menuMapper.selectByExample(example);
    }

    /**
     * 分页查询角色集合
     * @return
     */
    public List<Role> selectRoleList(Integer page, Integer rows){
        RoleExample example=new RoleExample();
        if(!Util.isNullOrEmpty(page)&&!Util.isNullOrEmpty(rows)){
            example.setLimitStart((page-1)*rows);
            example.setLimitEnd(rows);
        }
        example.setOrderByClause(" role_name desc");
        return roleMapper.selectByExample(example);
    }

    /**
     * 查询角色条数
     * @return
     */
    public Integer selectRoleCount(){
        return roleMapper.countByExample(null);
    }

    /**
     * 修改角色
     * @param role
     * @throws Exception
     */
    @Transactional
    public void editRole(Role role)throws Exception{
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    @Transactional
    public void insertRole(Role role)throws Exception{
        roleMapper.insertSelective(role);
    }

    /**
     * 删除角色
     * @param id
     * @throws Exception
     */
    @Transactional
    public void deleteRole(String id)throws Exception{
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除角色
     * @param ids
     * @throws Exception
     */
    @Transactional
    public void deleteMoreRole(String[] ids)throws Exception{
        for(String id:ids){
            roleMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 更新权限菜单
     * @param roleId
     * @param ids
     * @throws Exception
     */
    @Transactional
    public void insertRoleMenu(String roleId,String[] ids)throws Exception{
        RoleMenuExample example=new RoleMenuExample();
        RoleMenuExample.Criteria criteria=example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        example.or(criteria);
        roleMenuMapper.deleteByExample(example);
        for(String id:ids){
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setId(Util.UUID());
            roleMenu.setMenuId(id);
            roleMenu.setRoleId(roleId);
            roleMenuMapper.insertSelective(roleMenu);
        }
    }

    /**
     * 插入菜单
     * @param menu
     * @throws Exception
     */
    @Transactional
    public void insertMenu(Menu menu)throws Exception{
        menuMapper.insertSelective(menu);
    }

    /**
     * 修改菜单
     * @param menu
     * @throws Exception
     */
    @Transactional
    public void editMenu(Menu menu)throws Exception{
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 删除菜单
     * @param id
     * @throws Exception
     */
    @Transactional
    public void deleteMenu(String id)throws Exception{
        //TODO 此处只删除了当前id的菜单没有删除子集
        menuMapper.deleteByPrimaryKey(id);
    }
    /**
     * 菜单详情
     * @param id
     * @return
     */
    public Menu selectMenuDetail(String id){
        return menuMapper.selectByPrimaryKey(id);
    }
}
