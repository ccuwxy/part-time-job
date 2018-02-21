package com.proj.api.auth.info.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/15.
 */
public class ModifyPasswordRetGson {
    private int err_code = 0;
    private String reason = Reason.getReason(err_code);

    public int getErr_code() {
        return err_code;
    }

    public String getReason() {
        return reason;
    }
}
