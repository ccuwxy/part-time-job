package com.proj.api.auth.authorization.gson;

import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.utils.InputStrUtils;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class AuthorizationRecvGson {
    private String username;
    private String rand_str;
    private String pre_password;

    public String getUsername() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRand_str() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(rand_str);

    }

    public void setRand_str(String rand_str) {
        this.rand_str = rand_str;
    }

    public String getPre_password() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(pre_password);
    }

    public void setPre_password(String pre_password) {
        this.pre_password = pre_password;
    }
}
