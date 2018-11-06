package com.cloudtravel.cloudtravelwebservice.Exception;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;

public class InvalidParameterException extends BaseException {

    public InvalidParameterException() {
        super.setCode(ErrorCode.INVALID_PARAMETER);
    }
}
