package com.proj.api.exception.cert;

import com.proj.api.exception.error.CustomError;

/**
 * Created by jangitlau on 2017/12/12.
 */
public class UserCertAlreadyExistException extends CustomError{
    private static final int err_code=420;
    public UserCertAlreadyExistException() {
        super(err_code);
    }
}
