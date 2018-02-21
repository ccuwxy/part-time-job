package com.proj.api.auth.registration.gson;

import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.utils.InputStrUtils;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class RegistrationRecvGson {
    private String username;
    private String phone_num;
    private String password_key;
    private int type;

    public String getPhone_num() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(phone_num);
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getUsername() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_key() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(password_key);
    }

    public void setPassword_key(String password_key) {
        this.password_key = password_key;
    }

    public int getType() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(type);
    }

    public void setType(int type) {
        this.type = type;
    }
}
