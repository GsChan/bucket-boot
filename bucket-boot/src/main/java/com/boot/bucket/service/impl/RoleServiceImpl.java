package com.boot.bucket.service.impl;

import com.boot.bucket.entity.Role;
import com.boot.bucket.mapper.RoleMapper;
import com.boot.bucket.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role queryRoleById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public int saveRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteRoleById(Long id) {
        return roleMapper.deleteById(id);
    }
}
