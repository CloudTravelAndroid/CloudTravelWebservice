package com.cloudtravel.cloudtravelwebservice.Exception;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super.setCode(ErrorCode.UNAUTHORIZED);
    }
}
