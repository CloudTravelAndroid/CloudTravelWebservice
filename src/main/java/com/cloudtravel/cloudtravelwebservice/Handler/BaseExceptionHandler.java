package com.cloudtravel.cloudtravelwebservice.Handler;

import com.cloudtravel.cloudtravelwebservice.DTO.BaseResponse;
import com.cloudtravel.cloudtravelwebservice.Exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    public BaseResponse handleInvalidParameterException(InvalidParameterException e) {
        return new BaseResponse(e.getCode());
    }

    @ExceptionHandler(SignInException.class)
    @ResponseBody
    public BaseResponse handleSignInException(SignInException e) {
        return new BaseResponse(e.getCode());
    }

    @ExceptionHandler(SignUpException.class)
    @ResponseBody
    public BaseResponse handleSignUpException(SignUpException e) {
        return new BaseResponse(e.getCode());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public BaseResponse handleTokenExpiredException(TokenExpiredException e) {
        return new BaseResponse(e.getCode());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public BaseResponse handleSignInException(UnauthorizedException e) {
        return new BaseResponse(e.getCode());
    }
}
