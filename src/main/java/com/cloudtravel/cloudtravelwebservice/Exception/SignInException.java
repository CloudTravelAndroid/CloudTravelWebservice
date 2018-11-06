package com.cloudtravel.cloudtravelwebservice.Exception;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;

public class SignInException extends BaseException {

    public SignInException(ErrorCode code) {
        super.setCode(code);
    }
}
