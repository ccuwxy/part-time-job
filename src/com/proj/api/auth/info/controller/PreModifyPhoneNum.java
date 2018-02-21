package com.proj.api.auth.info.controller;

import com.proj.api.auth.backstage.gson.PreModifyPhoneNumGson;
import com.proj.api.database.KeyValueDatabase;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.JsonUtils;
import com.proj.api.utils.PhoneVerifyCodeUtils;

/**
 * Created by jangitlau on 2017/12/6.
 */
public class PreModifyPhoneNum {
    public PreModifyPhoneNum(String _sLoginId,String _sPhoneNum,String _sCheckCode) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException {
        AuthorizationUtils authorizationUtils=new AuthorizationUtils(_sLoginId);
        authorizationUtils.checkParams(_sLoginId+_sPhoneNum,_sCheckCode);

        String sVerificationCode= PhoneVerifyCodeUtils.send(_sPhoneNum);

        PreModifyPhoneNumGson preModifyPhoneNumGson=new PreModifyPhoneNumGson(
                authorizationUtils.getsUserId(),
                _sLoginId,
                _sPhoneNum,
                sVerificationCode
        );

        KeyValueDatabase kvConn=new KeyValueDatabase(PreModifyPhoneNumGson.sessionPrefix);
        kvConn.set(_sLoginId,JsonUtils.toJson(preModifyPhoneNumGson),PreModifyPhoneNumGson.iSessionExpire);
        kvConn.close();
    }
}
