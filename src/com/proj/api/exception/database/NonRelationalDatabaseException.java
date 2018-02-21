package com.proj.api.exception.database;

import com.proj.api.exception.error.CustomError;
import com.proj.api.exception.error.ErrGsonStructure;
import com.proj.api.exception.error.Reason;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.JsonUtils;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class NonRelationalDatabaseException extends CustomError {
    private static final int err_code = 501;
    public Exception main_exception;
    public Exception sub_exception;

    public NonRelationalDatabaseException(Exception _oException) {
        super(err_code);
        this.main_exception = _oException;
        _oException.printStackTrace();
    }

    public NonRelationalDatabaseException(Exception _oMain_exception, Exception _oSub_exception) {
        super(err_code);
        this.main_exception = _oMain_exception;
        this.sub_exception = _oSub_exception;
        _oMain_exception.printStackTrace();
        _oSub_exception.printStackTrace();
    }

    @Override
    public String getRetJson() {
        return JsonUtils.toJson(new ErrGsonStructure(super.getErr_code()
                , Reason.getReason(super.getErr_code())
                + " Cause by " + main_exception.getCause()));
    }
}
