package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/12/9.
 */
public class InvalidPhoneNumException extends CustomError{
    private static final int err_code = 417;
    protected InvalidPhoneNumException() {
        super(err_code);
    }
}
