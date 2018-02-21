package com.proj.api.exception.cert;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/12/12.
 */
public class InvalidCertTypeException extends CustomError{
    private static final int err_code=419;
    public InvalidCertTypeException() {
        super(err_code);
    }
}
