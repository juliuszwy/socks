package com.scoks.order.vo;

import lombok.Data;
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
public class GameResultVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String uuid;
    private double btc;
    private double ltc;
    private double eth;
    private double bbk;

}
