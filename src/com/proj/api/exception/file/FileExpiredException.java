package com.proj.api.exception.file;

import com.proj.api.exception.error.CustomError;

public class FileExpiredException extends CustomError{
    private static final int err_code = 423;
    public FileExpiredException() {
        super(err_code);
    }
}
