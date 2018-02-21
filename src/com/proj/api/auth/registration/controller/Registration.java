package com.proj.api.auth.registration.controller;

import com.proj.api.auth.registration.gson.PreRegistrationInfGson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.auth.InvalidOperationException;
import com.proj.api.exception.auth.UserAlreadyExistException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.auth.authorization.gson.LoggedInUserInfGson;
import com.proj.api.utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Registration {
    private String sLoginId;
    private String sUserId;
    private String sUsername;
    private String sPhoneNum;
    private int iType;
    private int iAuthority;
    private int iStatus;
    private String sPreToken;
    private int iExpire;

    public Registration(String _sUsername,String _sPhoneNum,String _sPasswordKey,int _iType) throws RelationalDatabaseException, NonRelationalDatabaseException, AESDecryptException, AESEncryptException, UserAlreadyExistException, MalformedJsonException, InvalidOperationException {
        this.iType = _iType;
        KeyValueDatabase kvConn = new KeyValueDatabase(PreRegistrationInfGson.sessionPrefix);
        if (!kvConn.exists(_sPhoneNum)) {
            kvConn.close();
            throw new InvalidOperationException();
        }
        PreRegistrationInfGson preRegistrationInfGson = JsonUtils.fromJson(kvConn.get(_sPhoneNum), PreRegistrationInfGson.class);
        String sClearPassword = "";
        try {
            sClearPassword = EncryptUtils.AES.decryptData(_sPasswordKey, preRegistrationInfGson.getRand_str()).replace("\0", "");
        } catch (Exception e) {
            kvConn.close();
            throw new AESDecryptException();
        }
        RelationalDatabase rConn = new RelationalDatabase();
        try {
            ResultSet result = rConn.doQuery("SELECT uuid FROM user_auth WHERE username=?", new String[]{_sUsername});
            if (result.first()) {
                kvConn.close();
                rConn.close();
                throw new UserAlreadyExistException();
            }
        } catch (SQLException e) {
            kvConn.close();
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        String sUserId = UUID.randomUUID().toString();
        System.out.println("Clear PWD:" + sClearPassword);
        String sTranPassword = SensitiveDataUtils.toTranpassword(sClearPassword);
        String sAuthPassword = SensitiveDataUtils.toAuthpassword(sClearPassword);
        rConn.doSQL("INSERT INTO user_auth(uuid,username,phone_num,tran_password,auth_password,type,authority,status) VALUES(?,?,?,?,?,?,?,?)"
                , new Object[]{sUserId, _sUsername, _sPhoneNum, sTranPassword, sAuthPassword, _iType, 0, 0});
        rConn.close();
        String sLoginId = SensitiveDataUtils.getLoginId(sUserId, 0);
        String sToken = RandomUtils.getRandomString(64);
        LoggedInUserInfGson loggedInUserInfGson = new LoggedInUserInfGson(
                sLoginId
                , sUserId
                , _sUsername
                , preRegistrationInfGson.getPhonenum()
                , _iType
                , 1
                , 1
                , sToken
                , System.currentTimeMillis());
        kvConn.setPrefix(LoggedInUserInfGson.sessionPrefix);
        kvConn.set(sLoginId, JsonUtils.toJson(loggedInUserInfGson), LoggedInUserInfGson.iSessionExpire);
        kvConn.close();
        this.sLoginId = sLoginId;
        this.sUserId = sUserId;
        this.sUsername = _sUsername;
        this.sPhoneNum = preRegistrationInfGson.getPhonenum();
        this.iType = _iType;
        this.iAuthority = 1;
        this.iStatus = 1;
        this.sPreToken = EncryptUtils.AES.encryptData(sToken, preRegistrationInfGson.getRand_str());
        this.iExpire = LoggedInUserInfGson.iSessionExpire;
    }

    public String getsLoginId() {
        return sLoginId;
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsPhoneNum() {
        return sPhoneNum;
    }

    public int getiType() {
        return iType;
    }

    public int getiAuthority() {
        return iAuthority;
    }

    public int getiStatus() {
        return iStatus;
    }

    public String getsPreToken() {
        return sPreToken;
    }

    public int getiExpire() {
        return iExpire;
    }
}
