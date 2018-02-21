package com.proj.api.auth.info.controller;

import com.proj.api.auth.backstage.gson.PreModifyPhoneNumGson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.JsonUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/12/6.
 */
public class ModifyPhoneNum {
    public ModifyPhoneNum(String _sLoginId,String _sPhoneCheckCode,String _sCheckCode) throws InvalidCheckCodeException, UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, AESDecryptException, InvalidPhoneVerificationCodeException, InvalidOperationException, RelationalDatabaseException, UserNotExistException {
        AuthorizationUtils authorizationUtils=new AuthorizationUtils(_sLoginId);
        authorizationUtils.checkParams(_sLoginId+_sPhoneCheckCode,_sCheckCode);
        String sRecvVerificationCode=authorizationUtils.getSensitiveData(_sPhoneCheckCode);

        KeyValueDatabase kvConn=new KeyValueDatabase(PreModifyPhoneNumGson.sessionPrefix);
        if(!kvConn.exists(_sLoginId)){
            kvConn.close();
            throw new InvalidPhoneVerificationCodeException();
        }
        PreModifyPhoneNumGson preModifyPhoneNumGson= JsonUtils.fromJson(kvConn.get(_sLoginId),PreModifyPhoneNumGson.class);
        if(preModifyPhoneNumGson.getsLoginId()!=_sLoginId){
            kvConn.close();
            throw new InvalidOperationException();
        }
        if(!preModifyPhoneNumGson.getsVerificationCode().equals(sRecvVerificationCode)){
            kvConn.close();
            throw new InvalidPhoneVerificationCodeException();
        }
        kvConn.del(_sLoginId);
        kvConn.close();

        RelationalDatabase rDbConn = new RelationalDatabase();
        ResultSet rs;
        try {
            rs = rDbConn.doQuery("SELECT UUID FROM USER_AUTH WHERE UUID = ?"
                    , new String[]{authorizationUtils.getsUserId()});
            if(rs.first()){
                rDbConn.doSQL("UPDATE USER_AUTH SET PHONE_NUM=? WHERE UUID=?"
                        ,new String[]{preModifyPhoneNumGson.getsPhoneNum(),preModifyPhoneNumGson.getsUserId()});
            }else{
                rDbConn.close();
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            rDbConn.close();
            throw new RelationalDatabaseException(e);
        }
        rDbConn.close();
    }
}

















