package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author julius
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Staff extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;

    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 油箱
     */
    private String email;

    /**
     * 职位 ： 1 boss 2 业务员 3 车间主任 4 生产员工
     */
    private Integer position;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 状态 ：0正常 1 禁用 2删除
     */
    private Integer state;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private List<Role> roles;


}
