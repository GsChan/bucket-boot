package com.boot.bucket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.bucket.entity.RoleFunc;
import com.boot.bucket.entity.UserRole;
import com.boot.bucket.mapper.RoleFuncMapper;
import com.boot.bucket.service.RoleFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统角色功能关联 服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-13
 */
@Service("roleFuncService")
@Transactional
public class RoleFuncServiceImpl extends ServiceImpl<RoleFuncMapper, RoleFunc> implements RoleFuncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleFuncServiceImpl.class);

    @Autowired
    private RoleFuncMapper roleFuncMapper;

    @Override
    public List<RoleFunc> selectByRoleId(Long roleId) {
        QueryWrapper<RoleFunc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleId", roleId);
        return roleFuncMapper.selectList(queryWrapper);
    }
}
