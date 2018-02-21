package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class UserNotExistException extends CustomError {
    private static final int err_code = 402;
    public UserNotExistException() {
        super(err_code);
    }
}
