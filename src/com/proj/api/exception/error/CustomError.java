package com.proj.api.exception.error;

import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.JsonUtils;

/**
 * Created by jangitlau on 2017/11/15.
 */
public abstract class CustomError extends Throwable{
    private int err_code;
    protected CustomError(int _err_code){
        this.err_code=_err_code;
    }

    public String getRetJson() {
        return JsonUtils.toJson(new ErrGsonStructure(err_code));
    }

    protected int getErr_code() {
        return err_code;
    }
}
