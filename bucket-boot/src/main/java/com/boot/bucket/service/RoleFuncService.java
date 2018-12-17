package com.boot.bucket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.bucket.entity.RoleFunc;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  系统角色功能关联 服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-13
 */
public interface RoleFuncService extends IService<RoleFunc> {

    /**
     *
     * @param roleId
     * @return
     */
    List<RoleFunc> selectByRoleId(Long roleId);
}
