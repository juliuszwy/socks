package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.dto.MachineWorkSum;
import com.scoks.order.entity.OrderProductWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-02-22
 */
@Mapper
public interface OrderProductWorkMapper extends BaseMapper<OrderProductWork> {
    List<OrderProductWork> listOrderProductWork(Page<OrderProductWork> page, @Param("where") OrderProductWork where);

    List<MachineWorkSum> sumMachineWork(MachineWorkSum form);

}
