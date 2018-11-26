package com.boot.bucket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
public interface UserService extends IService<User> {

    ResultDto queryUserPage(Page<User> page, UserVo userVo);
}
