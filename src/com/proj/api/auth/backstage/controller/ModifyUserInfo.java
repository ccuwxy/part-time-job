package com.proj.api.auth.backstage.controller;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.EncryptUtils;
import com.proj.api.utils.SensitiveDataUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by jangitlau on 2017/12/8.
 */
public class ModifyUserInfo {
    private String sUserId;
    private String sCheckCode;

    public ModifyUserInfo(String login_id,String user_id,String username,String phone_num,String password_key,int type,int authority,int status,String check_code) throws InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, RelationalDatabaseException, UserNotExistException, AESDecryptException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(login_id);
        //检查校验码是否正确
        authorizationUtils.checkParams(String.valueOf(authority) + login_id + password_key + phone_num + String.valueOf(status) + String.valueOf(type) + username, check_code);
        //权限验证
        //操作的用户不是后台用户
        if (authorizationUtils.getiType() != 3) {
            throw new InvalidOperationException();
        }
        //操作的用户是否达到可以操作的级别
        if (authorizationUtils.getiAuthority() <= 5) {
            throw new InvalidBackstageOperationException();
        }
        //操作的用户是否达到可以完全修改所有用户类型的级别
        if ((!(type == 1 || type == 2)) && authorizationUtils.getiAuthority() <= 10) {
            throw new InvalidBackstageOperationException();
        }

        String sClearPassword = authorizationUtils.getSensitiveData(password_key);//对上传的密码进行解密
        String sTranPassword = SensitiveDataUtils.toTranpassword(sClearPassword);//计算传输密码
        String sAuthPassword = SensitiveDataUtils.toAuthpassword(sClearPassword);//计算授权密码

        RelationalDatabase rDbConn = new RelationalDatabase();
        ResultSet rs;
        try {
            rs = rDbConn.doQuery("SELECT USERNAME,PHONE_NUM,AUTH_PASSWORD,TRAN_PASSWORD,TYPE,AUTHORITY,STATUS FROM USER_AUTH WHERE UUID = ?", new String[]{user_id});
            if (rs.first()) {
                username = username != "" ? username : rs.getString("username");
                phone_num = phone_num != "" ? phone_num : rs.getString("phone_num");
                type = type > 0 ? type : rs.getInt("type");
                authority = authority > 0 ? authority : rs.getInt("authority");
                status = status > 0 ? status : rs.getInt("status");
                sTranPassword = sClearPassword != "" ? sTranPassword : rs.getString("tran_password");
                sAuthPassword = sClearPassword != "" ? sAuthPassword : rs.getString("auth_password");
            } else {
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
        //开始修改用户
        rDbConn.doSQL("UPDATE USER_AUTH SET USERNAME=?,PHONE_NUM=?,AUTH_PASSWORD=?,TRAN_PASSWORD=?,TYPE=?,AUTHORITY=?,STATUS=? WHERE UUID=?"
                , new Object[]{username, phone_num, sAuthPassword, sTranPassword, type, authority, status, user_id});
        this.sUserId = user_id;
        this.sCheckCode = authorizationUtils.getCheckCode(this.sUserId);
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsCheckCode() {
        return sCheckCode;
    }
}
