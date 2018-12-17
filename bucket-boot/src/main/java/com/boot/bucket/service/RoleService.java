package com.boot.bucket.service;

import com.boot.bucket.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
public interface RoleService extends IService<Role> {

    Role queryRoleById(Long id);

    int saveRole(Role role);

    int updateRole(Role role);

    int deleteRoleById(Long id);
}
