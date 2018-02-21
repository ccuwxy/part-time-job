package com.proj.api.auth.registration.controller;

import com.proj.api.auth.registration.gson.PreRegistrationInfGson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.auth.PhoneAlreadyExistException;
import com.proj.api.exception.auth.UserAlreadyExistException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreRegistration {
    private String sKey;
    private String sPhoneNum;

    public PreRegistration(String _sPhoneNum) throws NonRelationalDatabaseException, RelationalDatabaseException, UserAlreadyExistException, AESEncryptException, PhoneAlreadyExistException, MalformedJsonException {
        RelationalDatabase rConn = new RelationalDatabase();
        ResultSet result = rConn.doQuery("SELECT uuid FROM user_auth WHERE phone_num=?", new String[]{_sPhoneNum});
        try {
            if (result.first()) {
                rConn.close();
                throw new PhoneAlreadyExistException();
            }
        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        rConn.close();
        String sRandomStr = RandomUtils.getRandomString(16);
        PreRegistrationInfGson preRegistrationInfGson = new PreRegistrationInfGson(_sPhoneNum, sRandomStr);
        KeyValueDatabase kvConn = new KeyValueDatabase(PreRegistrationInfGson.sessionPrefix);
        kvConn.set(_sPhoneNum, JsonUtils.toJson(preRegistrationInfGson), PreRegistrationInfGson.iSessionExpire);
        kvConn.close();
        String sPhoneVerifyCode = PhoneVerifyCodeUtils.send(_sPhoneNum);
        this.sKey = EncryptUtils.AES.encryptData(sRandomStr, sPhoneVerifyCode);
        this.sPhoneNum = _sPhoneNum;
    }


    public String getsKey() {
        return sKey;
    }

    public String getsPhoneNum() {
        return sPhoneNum;
    }
}
