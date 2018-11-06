package com.cloudtravel.cloudtravelwebservice.Exception;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;

public class TokenExpiredException extends BaseException {

    public TokenExpiredException() {
        super.setCode(ErrorCode.EXPIRED_TOKEN);
    }
}
