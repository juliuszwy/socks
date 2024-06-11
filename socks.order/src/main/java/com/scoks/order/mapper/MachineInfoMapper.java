package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-10-15
 */
@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfo> {
    List<MachineInfo> listMachineInfo(Page<MachineInfo> page, @Param("where") MachineInfo where);

}
