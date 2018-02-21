package com.proj.api.exception.cert;

import com.proj.api.exception.error.CustomError;

public class CertInfNotExistException extends CustomError{
    private static final int err_code=415;
    public CertInfNotExistException() {
        super(err_code);
    }
}
