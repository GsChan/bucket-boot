package com.boot.bucket.controller;


import com.boot.bucket.entity.Role;
import com.boot.bucket.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 根据用户id查询角色数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Role> queryRoleById(@PathVariable("id") Long id) {

        try {
            Role role = roleService.queryRoleById(id);
            if (null == role) {
                // 资源不存在，响应404
                return SERVER_NOT_FOUND;
            }
            // 200
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveRole(Role role) {

        try {
            roleService.saveRole(role);
            // 204
            return CREATED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 修改角色信息
     * 
     * @param role
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRole(Role role) {

        try {
            roleService.updateRole(role);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 根据id删除角色
     * 
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRole(@RequestParam(value = "id", defaultValue = "0") Long id) {

        try {
            if (id.intValue() == 0) {
                // 请求参数有误
                return BAD_REQUEST;
            }
            roleService.deleteRoleById(id);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    
}
