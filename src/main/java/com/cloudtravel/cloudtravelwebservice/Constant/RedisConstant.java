package com.cloudtravel.cloudtravelwebservice.Constant;

public interface RedisConstant {

    String TOKEN_FORMAT = "cloudtravel_token_%s";

    Integer EXPIRE_TIME = 2 * 24 * 60 * 60; // 2 days
}