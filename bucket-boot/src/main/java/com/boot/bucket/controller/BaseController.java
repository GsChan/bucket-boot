package com.boot.bucket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 服务端异常
     */
    public static final ResponseEntity SERVER_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    /**
     * 服务端资源不存在
     */
    public static final ResponseEntity SERVER_NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    /**
     * 请求参数有误
     */
    public static final ResponseEntity BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    /**
     * 操作执行已经成功，但是没有返回数据
     */
    public static final ResponseEntity SUCCESS_NO_CONTENT = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    /**
     * 对象创建成功
     */
    public static final ResponseEntity CREATED = ResponseEntity.status(HttpStatus.CREATED).build();





}
