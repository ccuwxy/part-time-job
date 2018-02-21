package com.proj.api.auth.backstage.gson;

/**
 * Created by jangitlau on 2017/12/9.
 */
public class PreModifyPhoneNumGson {
    public final static int iSessionExpire = 300;
    public final static String sessionPrefix = "pre_modify_phone_num_";

    private String sUserId;
    private String sLoginId;
    private String sPhoneNum;
    private String sVerificationCode;

    public PreModifyPhoneNumGson(String sUserId, String sLoginId, String sPhoneNum, String sVerificationCode) {
        this.sUserId = sUserId;
        this.sLoginId = sLoginId;
        this.sPhoneNum = sPhoneNum;
        this.sVerificationCode = sVerificationCode;
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsLoginId() {
        return sLoginId;
    }

    public String getsPhoneNum() {
        return sPhoneNum;
    }

    public String getsVerificationCode() {
        return sVerificationCode;
    }
}
