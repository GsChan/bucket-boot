package com.boot.bucket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bucket.common.vo.UserVo;
import com.boot.bucket.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 华仔
 * @since 2018-11-25
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserPage(Page page, @Param("userVo") UserVo userVo);
}
