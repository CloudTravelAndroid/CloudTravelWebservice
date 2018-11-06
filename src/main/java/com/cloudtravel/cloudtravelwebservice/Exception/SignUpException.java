package com.cloudtravel.cloudtravelwebservice.Exception;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;

public class SignUpException extends BaseException {

    public SignUpException(ErrorCode code) {
        super.setCode(code);
    }
}
