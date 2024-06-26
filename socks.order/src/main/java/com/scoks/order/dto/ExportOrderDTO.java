package com.scoks.order.dto;

import com.sargeraswang.util.ExcelUtil.ExcelCell;
import lombok.Data;

@Data
public class ExportOrderDTO {
    private Long id;
    @ExcelCell(index = 0)
    private String orderNo;
    @ExcelCell(index = 1)
    private String customer;
    @ExcelCell(index = 2)
    private String salesman;
    @ExcelCell(index = 3)
    private String device;
    @ExcelCell(index = 4)
    private String sewingHead;
    @ExcelCell(index = 5)
    private String itemNum;
    @ExcelCell(index = 6)
    private String mainYarn;
    @ExcelCell(index = 7)
    private String liningYarn;
    @ExcelCell(index = 8)
    private String size;
    @ExcelCell(index = 9)
    private Long targetNum;
    @ExcelCell(index = 10)
    private Long completedNum;
    @ExcelCell(index = 11)
    private Long finalizeNum;
    @ExcelCell(index = 12)
    private Long outTargetNum;
    @ExcelCell(index = 13)
    private Long outCompletedNum;
    @ExcelCell(index = 14)
    private String produceState;
    @ExcelCell(index = 15)
    private String finalizeState;

}