package com.boot.bucket.service.impl;

import com.boot.bucket.entity.Func;
import com.boot.bucket.mapper.FuncMapper;
import com.boot.bucket.service.FuncService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@Service("funcService")
@Transactional
public class FuncServiceImpl extends ServiceImpl<FuncMapper, Func> implements FuncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncServiceImpl.class);

    @Autowired
    private FuncMapper funcMapper;

    @Override
    public Func queryFuncById(Long id) {
        return funcMapper.selectById(id);
    }

    @Override
    public int saveFunc(Func func) {
        return funcMapper.insert(func);
    }

    @Override
    public int updateFunc(Func func) {
        return funcMapper.updateById(func);
    }

    @Override
    public int deleteFuncById(Long id) {
        return funcMapper.deleteById(id);
    }
}
