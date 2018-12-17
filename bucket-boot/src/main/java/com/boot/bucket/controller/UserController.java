package com.boot.bucket.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.dto.ResultDto;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import com.boot.bucket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询用户数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> queryUserById(@PathVariable("id") Long id) {

        try {
            User user = userService.queryUserById(id);
            if (null == user) {
                // 资源不存在，响应404
                return SERVER_NOT_FOUND;
            }
            // 200
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(User user) {

        try {
            userService.saveUser(user);
            // 204
            return CREATED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user) {

        try {
            userService.updateUser(user);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", defaultValue = "0") Long id) {

        try {
            if (id.intValue() == 0) {
                // 请求参数有误
                return BAD_REQUEST;
            }
            userService.deleteUserById(id);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    @RequestMapping("/page")
    public ResultDto queryUser(Page<User> page, UserVo userVo) {
        return userService.queryUserPage(page, userVo);
    }

}
