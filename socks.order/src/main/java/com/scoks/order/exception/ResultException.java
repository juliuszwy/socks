package com.scoks.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 5851018172703811098L;

    public ResultException(ResultStatus status){
        this.status = status;
    }
    private ResultStatus status;
}