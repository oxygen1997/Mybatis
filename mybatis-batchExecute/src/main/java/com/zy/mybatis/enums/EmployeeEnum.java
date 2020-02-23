package com.zy.mybatis.enums;

/**
 * 让数据库保存100，200，300；不保存枚举的类型名
 */
public enum EmployeeEnum {

    LOGIN(100, "登录"),
    LOGOUT(200, "登出"),
    REMOVE(300, "不存在");
    private Integer code;
    private String msg;

    EmployeeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static EmployeeEnum getEnum(Integer code) {
        switch (code) {
            case 100:
                return LOGIN;
            case 200:
                return LOGOUT;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }
}
