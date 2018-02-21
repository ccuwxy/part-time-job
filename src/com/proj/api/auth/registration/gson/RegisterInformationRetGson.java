package com.proj.api.auth.registration.gson;
/**
 * author:Jerry
 */
import com.proj.api.exception.error.Reason;

public class RegisterInformationRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(0);

    public int getErr_code() {
        return err_code;
    }

    public String getReason() {
        return reason;
    }
}
