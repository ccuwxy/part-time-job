package com.proj.api.exception.other;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/29.
 */
public class InvaildPathException extends CustomError{
    private static final int err_code = 416;
    public InvaildPathException() {
        super(err_code);
    }
}
