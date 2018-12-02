package com.boot.bucket.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.controller.UserController;
import com.boot.bucket.entity.User;
import com.boot.bucket.mapper.UserMapper;
import com.boot.bucket.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultDto queryUserPage(Page<User> page, UserVo userVo) {
        page.setRecords(userMapper.queryUserPage(page, userVo));
        return ResultDto.valueOfSuccess(page);
    }

    @Override
    public User queryUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteById(id);
    }

}
