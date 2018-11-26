package com.boot.bucket.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import com.boot.bucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    @ResponseBody
    public ResultDto queryUser(Page<User> page, UserVo userVo) {
        return userService.queryUserPage(page, userVo);
    }

}
