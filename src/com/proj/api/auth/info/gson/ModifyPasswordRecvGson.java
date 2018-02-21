package com.proj.api.auth.info.gson;

import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.utils.InputStrUtils;

/**
 * Created by jangitlau on 2017/11/15.
 */
public class ModifyPasswordRecvGson {
    private String user_id;
    private String new_password;
    private String check_code;

    public String getUser_id() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(user_id);
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNew_password() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(new_password);
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getCheck_code() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(check_code);
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }
}
