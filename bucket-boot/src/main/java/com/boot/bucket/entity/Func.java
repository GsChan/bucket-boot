package com.boot.bucket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 华仔
 * @since 2018-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_func")
public class Func extends Model<Func> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 子系统
     */
    private Integer systemId;

    /**
     * 真实子系统
     */
    private Integer realSystemId;

    /**
     * 菜单编号，唯一键
     */
    private String code;

    /**
     * 名字
     */
    private String name;

    /**
     * 菜单描述
     */
    private String remark;

    /**
     * url
     */
    private String url;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 父编号
     */
    private String parentCode;

    /**
     * 排序
     */
    private Integer sortId;

    /**
     * 是否有效
     */
    private Integer isValid;

    /**
     * 事件方法名称
     */
    private String eventName;

    /**
     * 元素类型
     */
    private Integer elementType;

    /**
     * 是否是叶子,0:不是,1:是
     */
    private Boolean isLeaf;

    /**
     * 是否开放
     */
    private String isOpen;

    /**
     * 功能序列
     */
    private String funcSeq;

    /**
     * 是否可见
     */
    private Integer isVisible;

    private Integer oldId;

    private String menuUrlMethod;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
