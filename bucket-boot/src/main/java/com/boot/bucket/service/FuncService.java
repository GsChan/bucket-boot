package com.boot.bucket.service;

import com.boot.bucket.entity.Func;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  系统功能 服务类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
public interface FuncService extends IService<Func> {

    Func queryFuncById(Long id);

    int saveFunc(Func func);

    int updateFunc(Func func);

    int deleteFuncById(Long id);
}
