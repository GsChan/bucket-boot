package com.boot.bucket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.bucket.entity.User;
import com.boot.bucket.entity.UserRole;
import com.boot.bucket.mapper.UserRoleMapper;
import com.boot.bucket.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联 服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-13
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> selectByUserId(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return userRoleMapper.selectList(queryWrapper);
    }
}
