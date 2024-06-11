package com.scoks.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.dto.MachineWorkSum;
import com.scoks.order.mapper.DictMapper;
import com.scoks.order.mapper.MachineInfoMapper;
import com.scoks.order.mapper.MachineModelMapper;
import com.scoks.order.mapper.OrderProductWorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl {
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private MachineInfoMapper machineInfoMapper;
    @Autowired
    private MachineModelMapper machineModelMapper;
    @Autowired
    private OrderProductWorkMapper orderProductWorkMapper;


    public List<MachineWorkSum> sumMachineWork(Page<MachineWorkSum> page, MachineWorkSum form) {
        return orderProductWorkMapper.sumMachineWork(form);
    }
}
