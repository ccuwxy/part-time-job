package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class UserAlreadyExistException extends CustomError{
    private static final int err_code = 403;
    public UserAlreadyExistException() {
        super(err_code);
    }
}
