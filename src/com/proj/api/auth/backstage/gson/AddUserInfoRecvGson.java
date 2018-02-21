package com.proj.api.auth.backstage.gson;

import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.utils.InputStrUtils;

/**
 * Created by jangitlau on 2017/12/8.
 */
public class AddUserInfoRecvGson {
    private String login_id;
    private String username;
    private String phone_num;
    private String password_key;
    private int type;
    private int authority;
    private int status;
    private String check_code;

    public String getLogin_id() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(login_id);
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getUsername() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_num() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(phone_num);
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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

    public int getAuthority() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(authority);
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getStatus() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheck_code() throws InvalidParamsException {
        return InputStrUtils.filterRequiredParameter(check_code);
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }
}
