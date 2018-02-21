package com.proj.api.exception.file;

import com.proj.api.exception.error.CustomError;

public class CanNotHandlerFileException extends CustomError {
    private static final int err_code = 422;
    public CanNotHandlerFileException() {
        super(err_code);
    }
}
