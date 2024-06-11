package com.scoks.order.vo;

import com.scoks.order.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author julius
 * @since 2019-10-31
 */
@Data
@Accessors(chain = true)
public class GameUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String loginName;

    private String pwd;

    private String nick;

    private Long createTime;

    private Long updateTime;

    private Long sharer;




}
