package com.proj.api.exception.file;

import com.proj.api.exception.error.CustomError;

public class MongoDbException extends CustomError {
    private static final int err_code = 421;
    public MongoDbException() {
        super(err_code);
    }
}
