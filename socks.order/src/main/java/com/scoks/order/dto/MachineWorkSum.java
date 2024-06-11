package com.scoks.order.dto;

import lombok.Data;

@Data
public class MachineWorkSum {
    private String machineNum;
    private String pinNum;
    private String pinType;
    private Long sum;
    private String workName;
    private Integer sumType;
    private Long startTime;
    private Long endTime;

}
