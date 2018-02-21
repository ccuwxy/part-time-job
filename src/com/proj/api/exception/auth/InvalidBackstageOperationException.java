package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

public class InvalidBackstageOperationException extends CustomError{
    private static final int err_code = 412;
    public InvalidBackstageOperationException() {
        super(err_code);
    }
}
