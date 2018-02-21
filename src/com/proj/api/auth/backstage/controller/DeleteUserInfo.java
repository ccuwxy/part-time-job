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
public class DeleteUserInfo {
    private String sUserId;
    private String sCheckCode;

    public DeleteUserInfo(String login_id, String user_id, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, RelationalDatabaseException, UserNotExistException {
        AuthorizationUtils authorizationUtil = new AuthorizationUtils(login_id);
        authorizationUtil.checkParams(login_id + user_id, check_code);
        if (authorizationUtil.getiType() != 3) {
            throw new InvalidOperationException();
        }
        if (authorizationUtil.getiAuthority() <= 10) {
            throw new InvalidBackstageOperationException();
        }
        if (login_id.contains(user_id)) {
            throw new InvalidBackstageOperationException();
        }
        //检查用户是否存在
        RelationalDatabase rDbConn = new RelationalDatabase();
        ResultSet rs;
        try {
            rs = rDbConn.doQuery("SELECT UUID FROM USER_AUTH WHERE UUID = ?", new String[]{user_id});
            if (!rs.first()) {
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
        //删除用户
        rDbConn.doSQL("DELETE FROM USER_AUTH WHERE UUID=?", new String[]{user_id});
        this.sUserId = user_id;
        this.sCheckCode = authorizationUtil.getCheckCode(this.sUserId);
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsCheckCode() {
        return sCheckCode;
    }
}
