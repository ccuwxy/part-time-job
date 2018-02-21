package com.proj.api.auth.authorization.gson;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class PreAuthorizationGson {
    public final static int iSessionExpire = 60;
    public final static String sessionPrefix = "user_preauth_";

    private String sUserId;
    private String sPhoneNum;
    private String sUserName;
    private String sTranPassword;
    private String sAuthPassword;
    private String sRandomKey;
    private int iType;
    private int iAuthority;
    private int iStatus;

    public PreAuthorizationGson(String sId, String sPhoneNum, String sUserName, String sTranPassword, String sAuthPassword, String sRandomKey, int iType, int iAuthority, int iStatus) {
        this.sUserId = sId;
        this.sPhoneNum = sPhoneNum;
        this.sUserName = sUserName;
        this.sTranPassword = sTranPassword;
        this.sAuthPassword = sAuthPassword;
        this.sRandomKey = sRandomKey;
        this.iType = iType;
        this.iAuthority = iAuthority;
        this.iStatus = iStatus;
    }


    public static int getiSessionExpire() {
        return iSessionExpire;
    }

    public static String getSessionPrefix() {
        return sessionPrefix;
    }

    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsPhoneNum() {
        return sPhoneNum;
    }

    public void setsPhoneNum(String sPhoneNum) {
        this.sPhoneNum = sPhoneNum;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsTranPassword() {
        return sTranPassword;
    }

    public void setsTranPassword(String sTranPassword) {
        this.sTranPassword = sTranPassword;
    }

    public String getsAuthPassword() {
        return sAuthPassword;
    }

    public void setsAuthPassword(String sAuthPassword) {
        this.sAuthPassword = sAuthPassword;
    }

    public String getsRandomKey() {
        return sRandomKey;
    }

    public void setsRandomKey(String sRandomKey) {
        this.sRandomKey = sRandomKey;
    }

    public int getiType() {
        return iType;
    }

    public void setiType(int iType) {
        this.iType = iType;
    }

    public int getiAuthority() {
        return iAuthority;
    }

    public void setiAuthority(int iAuthority) {
        this.iAuthority = iAuthority;
    }

    public int getiStatus() {
        return iStatus;
    }

    public void setiStatus(int iStatus) {
        this.iStatus = iStatus;
    }
}
