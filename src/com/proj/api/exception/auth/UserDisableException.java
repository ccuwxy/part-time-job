package com.proj.api.exception.auth;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/18.
 */
public class UserDisableException extends CustomError {
    private static final int err_code = 413;

    public UserDisableException() {
        super(err_code);
    }
}
