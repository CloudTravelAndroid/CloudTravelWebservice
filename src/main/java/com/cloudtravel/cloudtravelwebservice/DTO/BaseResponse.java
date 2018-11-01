package com.cloudtravel.cloudtravelwebservice.DTO;

import com.cloudtravel.cloudtravelwebservice.Enum.ErrorCode;
import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer status;
    private String message;
    private T object;

    public BaseResponse(ErrorCode errorCode) {
        this.status = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BaseResponse() {
    }
}
