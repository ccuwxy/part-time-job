package com.proj.api.exception.other;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class InvalidParamsException extends CustomError{
    private static final int err_code = 401;
    public InvalidParamsException() {
        super(err_code);
    }
}
