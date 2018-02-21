package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/5.
 */
public class UserNotAuthorizedException extends CustomError{
    private static final int err_code = 410;
    public UserNotAuthorizedException() {
        super(err_code);
    }
}
