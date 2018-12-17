package com.boot.bucket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *  角色功能关联
 * </p>
 *
 * @author 华仔
 * @since 2018-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_function")
public class RoleFunc {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long RoleId;

    /**
     * 功能ID
     */
    private Long funcId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人员
     */
    private Long createUser;

}
