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
public class GameUserUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Double btc;

    private Double ltc;

    private Double eth;

    private Double bbk;
    private Integer gameTimes;

    private Long operator;//操作人



}
