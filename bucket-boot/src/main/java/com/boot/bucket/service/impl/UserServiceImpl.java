package com.boot.bucket.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import com.boot.bucket.mapper.UserMapper;
import com.boot.bucket.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultDto queryUserPage(Page<User> page, UserVo userVo) {
        page.setRecords(userMapper.queryUserPage(page, userVo));
        return ResultDto.valueOfSuccess(page);
    }

}
