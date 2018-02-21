package com.proj.api.utils;

import com.proj.api.database.KeyValueDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.auth.authorization.gson.LoggedInUserInfGson;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by jangitlau on 2017/11/5.
 */
public class AuthorizationUtils {
    private String sLoginId;
    private String sUserId;
    private String sUserName;
    private String sPhoneNum;
    private int iType;
    private int iAuthority;
    private int iStatus;
    private String sToken;
    private long lLoginTime;

    public AuthorizationUtils(String _sLoginId) throws NonRelationalDatabaseException, UserNotAuthorizedException, MalformedJsonException, UserDisableException {
        KeyValueDatabase kvConn=new KeyValueDatabase(LoggedInUserInfGson.sessionPrefix);
        if(!kvConn.exists(_sLoginId)){
            kvConn.close();
            throw new UserNotAuthorizedException();
        }
        LoggedInUserInfGson loggedInUserInfGson=JsonUtils.fromJson(kvConn.get(_sLoginId),LoggedInUserInfGson.class);
        kvConn.close();
        if(loggedInUserInfGson.getiStatus()==3){
            throw new UserDisableException();
        }
        this.sUserId =loggedInUserInfGson.getsUserId();
        this.sUserName=loggedInUserInfGson.getsUserName();
        this.sPhoneNum=loggedInUserInfGson.getsPhoneNum();
        this.iType=loggedInUserInfGson.getiType();
        this.iAuthority=loggedInUserInfGson.getiAuthority();
        this.iStatus=loggedInUserInfGson.getiStatus();
        this.sToken=loggedInUserInfGson.getsToken();
        this.lLoginTime=loggedInUserInfGson.getlLoginTime();
    }

    //验证参数是否正确，输入验证参数按照字母顺序排列的值以及验证码，错误抛出异常(全部小写字母)
    public void checkParams(String _sParamSet,String _CheckCode) throws InvalidCheckCodeException {
        System.out.println(_sParamSet+this.sToken);
        System.out.println(DigestUtils.md5Hex(_sParamSet+this.sToken));
        if(!DigestUtils.md5Hex(_sParamSet+this.sToken).toLowerCase().equals(_CheckCode.toLowerCase())){
            throw new InvalidCheckCodeException();
        }
    }

    //输入参数获取校验码
    public String getCheckCode(String _sParamSet){
        return DigestUtils.md5Hex(_sParamSet+this.getsToken());
    }

    //获取由Token加密的明文数据
    public String getSensitiveData(String _sCiphertext) throws AESDecryptException {
        return EncryptUtils.AES.decryptData(_sCiphertext,this.getsToken());
    }

    //登出
    public void doLogOut() throws NonRelationalDatabaseException {
        KeyValueDatabase kvConn=new KeyValueDatabase(LoggedInUserInfGson.sessionPrefix);
        if(kvConn.exists(sUserId)){
            kvConn.del(sUserId);
        }
        kvConn.close();
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsUserName() {
        return sUserName;
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

    public String getsToken() {
        return sToken;
    }

    public long getlLoginTime() {
        return lLoginTime;
    }
}
