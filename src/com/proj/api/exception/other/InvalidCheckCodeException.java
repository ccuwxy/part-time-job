package com.proj.api.exception.other;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/5.
 */
public class InvalidCheckCodeException extends CustomError{
    private static final int err_code = 408;
    public InvalidCheckCodeException() {
        super(err_code);
    }
}
