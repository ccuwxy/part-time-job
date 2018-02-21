package com.proj.api.auth.backstage.controller;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/12/8.
 */
public class GetUserInfo {
    private String user_id;
    private String username;
    private String phone_num;
    private int type;
    private int authority;
    private int status;
    private String check_code;

    public GetUserInfo(String login_id, String user_id, String check_code) throws InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, RelationalDatabaseException, UserNotExistException {
        AuthorizationUtils authorizationUtil = new AuthorizationUtils(login_id);
        authorizationUtil.checkParams(login_id + user_id, check_code);
        if (authorizationUtil.getiType() != 3) {
            throw new InvalidOperationException();
        }
        if (authorizationUtil.getiAuthority() <= 2) {
            throw new InvalidBackstageOperationException();
        }
        RelationalDatabase rDbConn = new RelationalDatabase();
        ResultSet rs;
        try {
            rs = rDbConn.doQuery("SELECT UUID,USERNAME,PHONE_NUM,TYPE,AUTHORITY,STATUS FROM USER_AUTH WHERE UUID = ?", new String[]{user_id});
            if (rs.first()) {
                this.user_id = rs.getString("uuid");
                this.username = rs.getString("username");
                this.phone_num = rs.getString("phone_num");
                this.type = rs.getInt("type");
                this.authority = rs.getInt("authority");
                this.status = rs.getInt("status");
            } else {
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
        this.check_code = authorizationUtil.getCheckCode(String.valueOf(authority) + phone_num + String.valueOf(status) + String.valueOf(type) + user_id + username);
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public int getType() {
        return type;
    }

    public int getAuthority() {
        return authority;
    }

    public int getStatus() {
        return status;
    }

    public String getCheck_code() {
        return check_code;
    }
}
