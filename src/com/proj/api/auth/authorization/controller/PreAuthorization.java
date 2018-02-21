package com.proj.api.auth.authorization.controller;

import com.proj.api.auth.authorization.gson.PreAuthorizationGson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.auth.UserNotExistException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.EncryptUtils;
import com.proj.api.utils.JsonUtils;
import com.proj.api.utils.RandomUtils;
import redis.clients.jedis.exceptions.JedisException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class PreAuthorization {
    private RelationalDatabase dbConn = null;
    private KeyValueDatabase jedisConn = null;

    private String sUsername;
    private String sKey;

    public PreAuthorization(String _sUsername) throws UserNotExistException, RelationalDatabaseException, NonRelationalDatabaseException, AESEncryptException, MalformedJsonException {
        this.sUsername = _sUsername;
        String sRandomStr = RandomUtils.getRandomString(16);
        int iType, iAuthority, iStatus;
        String sId, sUsername, sPhoneNum, sTranPassword, sAuthPassword;
        try {
            dbConn = new RelationalDatabase();
            ResultSet result = dbConn.doQuery("SELECT uuid,username,phone_num,tran_password,auth_password,type,authority,status "
                    + " FROM user_auth WHERE username=? or phone_num=?", new String[]{this.sUsername, this.sUsername});
            if (result.first()) {
                sId = result.getString("uuid");
                sUsername = result.getString("username");
                sPhoneNum = result.getString("phone_num");
                sTranPassword = result.getString("tran_password");
                sAuthPassword = result.getString("auth_password");
                iType = result.getInt("type");
                iAuthority = result.getInt("authority");
                iStatus = result.getInt("status");
            } else {
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        } finally {
            dbConn.close();
        }
        //construct session msg
        String session_msg = JsonUtils.toJson(new PreAuthorizationGson(sId, sPhoneNum, sUsername, sTranPassword, sAuthPassword, sRandomStr, iType, iAuthority, iStatus));
        //load jedis and put session msg
        try {
            jedisConn = new KeyValueDatabase(PreAuthorizationGson.sessionPrefix);
            jedisConn.set(_sUsername, session_msg, PreAuthorizationGson.iSessionExpire);
        } catch (JedisException e) {
            throw new NonRelationalDatabaseException(e);
        } finally {
            jedisConn.close();
        }
        this.sKey = EncryptUtils.AES.encryptData(sRandomStr, sTranPassword);
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsKey() {
        return sKey;
    }
}
