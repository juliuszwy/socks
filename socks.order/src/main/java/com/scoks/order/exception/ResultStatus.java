package com.scoks.order.exception;

/**
 * @author julius
 */
public enum ResultStatus {
    /**
     * 请求成功
     */
    SUCCESS(0, "成功"),

    /**
     * 请求失败
     */
    ERROR(-1, "系统异常"),

    NOT_LOGGED_IN(-2, "Not logged in"),
    PARAM_ERR(-3, "参数错误"),
    UPDATE_ERR(-4, "操作失败请重试"),
    DUPLICATE_ERR(-5, "重复创建"),
    /**
     * 未授权
     */
    UNAUTHORIZED(-100, "未经授权"),

    /**
     * 请求无权限
     */
    FORBIDDEN(-101, "无权限"),
    LOGINNAME_EXIST(-102, "登陆名已存在"),
    LOGIN_ERR(-103, "账号或密码错误"),
    ORDER_STATE_ERR(-110, "订单不存在或者已经完成"),

    ORDER_REPEAT(-111, "已经存在重复的订单号"),

    PRODUCT_OUT_LIMIT(-112, "外发数量超过款号总目标"),

    PRODUCT_STATE_ERR(-113, "产品已删除"),

    MATERIAL_NOT_ENOUGH(-200, "原料不足扣除数量");

    private int code;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}