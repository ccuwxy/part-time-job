package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/12/9.
 */
public class InvalidPhoneVerificationCodeException extends CustomError{
    private static final int err_code = 418;
    public InvalidPhoneVerificationCodeException() {
        super(err_code);
    }
}
