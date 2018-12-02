package com.boot.bucket.controller;


import com.boot.bucket.entity.Func;
import com.boot.bucket.service.FuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@RestController
@RequestMapping("/func")
public class FuncController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncController.class);

    @Autowired
    private FuncService funcService;

    /**
     * 根据用户id查询功能数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Func> queryFuncById(@PathVariable("id") Long id) {

        try {
            Func func = funcService.queryFuncById(id);
            if (null == func) {
                // 资源不存在，响应404
                return SERVER_NOT_FOUND;
            }
            // 200
            return ResponseEntity.ok(func);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 新增功能
     *
     * @param func
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveFunc(Func func) {

        try {
            funcService.saveFunc(func);
            // 204
            return CREATED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 修改功能信息
     *
     * @param func
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateFunc(Func func) {

        try {
            funcService.updateFunc(func);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }

    /**
     * 根据id删除功能
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFunc(@RequestParam(value = "id", defaultValue = "0") Long id) {

        try {
            if (id.intValue() == 0) {
                // 请求参数有误
                return BAD_REQUEST;
            }
            funcService.deleteFuncById(id);
            // 204
            return SUCCESS_NO_CONTENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return SERVER_ERROR;
    }
    
}
