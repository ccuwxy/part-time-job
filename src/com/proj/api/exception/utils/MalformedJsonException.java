package com.proj.api.exception.utils;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/15.
 */
public class MalformedJsonException extends CustomError{
    private static final int err_code = 405;
    public MalformedJsonException() {
        super(err_code);
    }
}
