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
public class AddUserInfo {
    private String sUserId;
    private String sCheckCode;
    public AddUserInfo(String login_id,String username,String phone_num,String password_key,int type,int authority,int status,String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, RelationalDatabaseException, UserAlreadyExistException, PhoneAlreadyExistException, AESDecryptException {
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
        //检查用户是否存在
        RelationalDatabase rDbConn = new RelationalDatabase();
        ResultSet rs;
        try {
            rs = rDbConn.doQuery("SELECT UUID FROM USER_AUTH WHERE USERNAME OR PHONE_NUM = ?", new String[]{username});
            if (rs.first()) {
                throw new UserAlreadyExistException();
            }
            rs = rDbConn.doQuery("SELECT UUID FROM USER_AUTH WHERE USERNAME OR PHONE_NUM = ?", new String[]{phone_num});
            if (rs.first()) {
                throw new PhoneAlreadyExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
        //添加用户
        String sClearPassword = EncryptUtils.AES.decryptData(password_key, authorizationUtils.getsToken());//对上传的密码进行解密
        String sUserId = UUID.randomUUID().toString();//新建uuid
        String sTranPassword = SensitiveDataUtils.toTranpassword(sClearPassword);//计算传输密码
        String sAuthPassword = SensitiveDataUtils.toAuthpassword(sClearPassword);//计算授权密码
        rDbConn.doSQL("INSERT INTO user_auth(uuid,username,phone_num,tran_password,auth_password,type,authority,status) VALUES(?,?,?,?,?,?,?,?)"
                , new Object[]{sUserId, username, phone_num, sTranPassword, sAuthPassword, type, authority, status});
        this.sUserId = sUserId;
        this.sCheckCode = authorizationUtils.getCheckCode(this.sUserId);
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsCheckCode() {
        return sCheckCode;
    }
}
