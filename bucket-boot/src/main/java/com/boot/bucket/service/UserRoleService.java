package com.boot.bucket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.bucket.entity.UserRole;

import java.util.List;

/**
 * <p>
 *  系统用户角色关联 服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-13
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据userId获取 用户角色关系
     * @param userId
     * @return
     */
    List<UserRole> selectByUserId(Long userId);



}
