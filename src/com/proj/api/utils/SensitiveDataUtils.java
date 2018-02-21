package com.proj.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class SensitiveDataUtils {
    public static final String sAuthPasswordSalt="";
    public static final String sPrePasswordSalt="";
    public static final String sTranPasswordSalt="";

    public static String toAuthpassword(String _sClearPassword){
        return DigestUtils.md5Hex(
                DigestUtils.md5Hex(_sClearPassword + SensitiveDataUtils.sPrePasswordSalt)
                        + SensitiveDataUtils.sAuthPasswordSalt);
    }

    public static String toTranpassword(String _sClearPassword){
        return DigestUtils.md5Hex(_sClearPassword + SensitiveDataUtils.sTranPasswordSalt);
    }

    public static String getLoginId(String _sUserId,int _sMethod){
        return _sUserId+"-"+String.valueOf(_sMethod);
    }

    private static final String sStuCertPrivateKey="";

    public static String doStuCertInfEncrypt(String sUserId,String sStuId,String sStuPwd,String sIdCode
            ,String sIdFrontToken,String sIdBackToken){
        Map<String,String> infMap=new HashMap<>();
        infMap.put("user_id",sUserId);
        infMap.put("stu_id",sStuId);
        infMap.put("stu_pwd",sStuPwd);
        infMap.put("id_code",sIdCode);
        infMap.put("id_front_pic",sIdFrontToken);
        infMap.put("id_back_pic",sIdBackToken);
        return EncryptUtils.RSA.encryptData(JsonUtils.toJson(infMap),sStuCertPrivateKey);
    }
}
