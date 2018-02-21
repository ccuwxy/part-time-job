package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/7.
 */
public class PhoneAlreadyExistException extends CustomError {
    private static final int err_code = 407;
    public PhoneAlreadyExistException() {
        super(err_code);
    }
}
