package org.eightbit.damdda.DamDda.common;

import org.springframework.http.HttpStatus;

public class ErrorHandler {
    private final String message;
    private final HttpStatus status;

    public ErrorHandler(CustomErrorCode customErrorCode) {
        this.message = customErrorCode.getMessage();
        this.status = customErrorCode.getStatus();
    }
}
