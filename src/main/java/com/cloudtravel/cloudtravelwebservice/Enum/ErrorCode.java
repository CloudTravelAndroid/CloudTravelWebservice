package com.cloudtravel.cloudtravelwebservice.Enum;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SUCCESS(0, "成功"),
    INCORRECT_PASSWORD(1, "密码错误"),
    USERNAME_NOT_EXIST(2, "用户名不存在"),
    UNAUTHORIZED(3, "没有权限"),
    INVALID_PARAMETER(4, "参数错误"),
    USER_NOT_EXIST(5, "用户不存在"),
    USERNAME_ALREADY_EXIST(6, "用户名已存在");

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
