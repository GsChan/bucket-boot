package com.boot.bucket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  系统用户 服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
public interface UserService extends IService<User> {


    ResultDto queryUserPage(Page<User> page, UserVo userVo);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User queryUserById(Long id);

    /**
     * 新增用户信息
     * @param user
     */
    int saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    int updateUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     */
    int deleteUserById(Long id);

    /**
     * 根据userName获取用户信息
     * @param userName
     * @return
     */
    User getByUserName(String userName);
}
