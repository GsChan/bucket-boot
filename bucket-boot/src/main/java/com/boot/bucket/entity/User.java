package com.boot.bucket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 匿名
     */
    private String nickName;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 0:正常，1:挂起，2:注销，3:锁定
     */
    private String status;

    /**
     * 密码错误次数
     */
    private Integer errorCount;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastestLogin;

    /**
     * 用户身份证号
     */
    private String identityCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 微信号
     */
    private String weixinCode;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 密码是否修改
     */
    private Boolean isModify;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private String updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
