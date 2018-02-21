package com.proj.api.exception.utils;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class AESEncryptException extends CustomError{
    private static final int err_code = 405;
    public AESEncryptException() {
        super(err_code);
    }
}
