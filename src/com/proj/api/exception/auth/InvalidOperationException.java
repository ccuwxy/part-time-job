package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class InvalidOperationException extends CustomError{
    private static final int err_code = 409;
    public InvalidOperationException() {
        super(err_code);
    }
}
