package com.proj.api.utils;

import com.proj.api.exception.database.NonRelationalDatabaseException;
import redis.clients.jedis.Jedis;
import com.proj.api.database.KeyValueDatabase;

public class Cache {
    private String sId;
    private String sUserName;
    private String iType;
    private String iAuthority;
    private String iStatus;
    private String sToken;
    private String lLoginTime;
    public Cache() throws NonRelationalDatabaseException {
        Jedis jedis = new Jedis("localhost");
        KeyValueDatabase keyValueDatabase = new KeyValueDatabase("user_inf_");
        keyValueDatabase.set("iId", "15943183488", 180*24*60*60);
        keyValueDatabase.set("sUserName", "wxywxy", 180*24*60*60);
        keyValueDatabase.set("iType", "1", 180*24*60*60);
        keyValueDatabase.set("iAuthority", "1", 180*24*60*60);
        keyValueDatabase.set("iStatus", "1", 180*24*60*60);
        keyValueDatabase.set("sToken", "1", 180*24*60*60);
        keyValueDatabase.set("lLoginTime", "111", 180*24*60*60);

//        sId = jedis.get("sID");
//        sUserName = jedis.get("sUserName");
//        iType = jedis.get("iType");
//        iAuthority = jedis.get("iAuthority");
//        iStatus = jedis.get("iStatus");
//        sToken = jedis.get("sToken");
//        lLoginTime = jedis.get("lLoginTime");
    }
    public String getID(){
        return sId;
    }
    public String getUserName(){
        return sUserName;
    }
    public String getType(){
        return iType;
    }
    public String getStatus(){
        return iStatus;
    }
    public String getAuthority(){
        return iAuthority;
    }
    public String getToken(){
        return sToken;
    }
    public String getLoginTime(){
        return lLoginTime;
    }


}
