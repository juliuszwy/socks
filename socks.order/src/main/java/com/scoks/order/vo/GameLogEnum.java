package com.scoks.order.vo;

public enum GameLogEnum {

    SHAER(1,"分享"),
    REGISTER(2,"注册"),
    UPDATE_GAME_TIME(3," 更新 用户游戏次数 "),
    GAME_RESULT_RECORD(4," 游戏结算数据记录 "),
    UPDATE_GAME_USER_COIN(5," 更新 用户游戏币 "),
    ;
    GameLogEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private int type;
    private String desc;

    public int type() {
        return type;
    }

    public String desc() {
        return desc;
    }

}
