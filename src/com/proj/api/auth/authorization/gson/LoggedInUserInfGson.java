package com.proj.api.auth.authorization.gson;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class LoggedInUserInfGson {
    public final static int iSessionExpire = 120;
    public final static String sessionPrefix = "user_inf_";

    private String sLoginId;
    private String sUserId;
    private String sUserName;
    private String sPhoneNum;
    private int iType;
    private int iAuthority;
    private int iStatus;
    private String sToken;
    private long lLoginTime;

    public LoggedInUserInfGson(String sLoginId, String sUserId, String sUserName, String sPhoneNum, int iType, int iAuthority, int iStatus, String sToken, long lLoginTime) {
        this.sLoginId = sLoginId;
        this.sUserId = sUserId;
        this.sUserName = sUserName;
        this.sPhoneNum = sPhoneNum;
        this.iType = iType;
        this.iAuthority = iAuthority;
        this.iStatus = iStatus;
        this.sToken = sToken;
        this.lLoginTime = lLoginTime;
    }

    public static int getiSessionExpire() {
        return iSessionExpire;
    }

    public static String getSessionPrefix() {
        return sessionPrefix;
    }

    public String getsLoginId() {
        return sLoginId;
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
