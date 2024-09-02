package com.beecommerce.exception;

import lombok.Getter;

@Getter
public class Exception extends RuntimeException {
    private  ErrorCode errorCode;
    private  SuccessCode successCode;

    public Exception(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public Exception(SuccessCode successCode) {
        super(successCode.getMessage());
        this.successCode = successCode;
    }

}
