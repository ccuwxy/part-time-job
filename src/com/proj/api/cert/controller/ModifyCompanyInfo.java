package com.proj.api.cert.controller;
/**
 * author:Jerry
 */
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.RandomUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyCompanyInfo {
    private boolean bCoName;
    private boolean bConickName;
    private boolean bIndustryType;
    private boolean bCoType;
    private boolean bOrgCode;
    private boolean bBizCode;
    private boolean bOrgPic;
    private boolean bBizPic;
    private boolean bCoScale;
    private boolean bCoDesc;
    private boolean bContactPerson;
    private boolean bContactPhone;
    private boolean bContactMail;
    private boolean bCoSite;
    private boolean bCoPic;
    private boolean bCoCity;
    private boolean bCoAddr;

    public ModifyCompanyInfo(String action, String auth_id, String user_id, int cert_status, String co_name, String co_nickname
            , int industry_type, int co_type, String org_code, String biz_code, String org_pic, String biz_pic, int co_scale
            , String co_desc, String contact_person, String contact_phone, String contact_mail, String co_site, String[] co_pic
            , int co_city, String co_addr, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException,
            MalformedJsonException, InvalidOperationException, InvalidParamsException, RelationalDatabaseException, SQLException, UserNotExistException, InvalidCheckCodeException, InvalidBackstageOperationException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(auth_id);
        authorizationUtils.checkParams(action+auth_id+biz_code+biz_pic+cert_status+co_addr+co_city
                +co_desc+co_name+co_nickname+co_pic+co_scale+co_site+co_type+contact_mail+contact_person+contact_phone
                +industry_type+org_code+org_pic+user_id,check_code);
        if (authorizationUtils.getiType() != 3) {
            throw new InvalidOperationException();
        }
        if(authorizationUtils.getiAuthority() < 5){
            throw new InvalidBackstageOperationException();
        }
        switch (action) {
            case "add":
                addCompany(user_id, cert_status, co_name, co_nickname, industry_type, co_type, org_code,
                        biz_code, org_pic, biz_pic, co_scale, co_desc, contact_person, contact_phone,
                        contact_mail, co_site, co_pic, co_city, co_addr, check_code);
                break;
            case "del":
                delCompany(user_id);
                break;
            case "mod":
                modCompany(user_id, cert_status, co_name, co_nickname, industry_type, co_type, org_code,
                        biz_code, org_pic, biz_pic, co_scale, co_desc, contact_person, contact_phone,
                        contact_mail, co_site, co_pic, co_city, co_addr, check_code);
                break;
            default:
                throw new InvalidParamsException();
        }
    }

    private void addCompany(String user_id, int cert_status, String co_name, String co_nickname
            , int industry_type, int co_type, String org_code, String biz_code, String org_pic, String biz_pic, int co_scale
            , String co_desc, String contact_person, String contact_phone, String contact_mail, String co_site, String[] co_pic
            , int co_city, String co_addr, String check_code) throws RelationalDatabaseException {
        RelationalDatabase rConn = new RelationalDatabase();
        String uuid = RandomUtils.getUUID();
        rConn.doSQL("insert INTO company_inf (uuid,status,co_name,co_nickname," +
                "industry_type,co_type,org_code,biz_code,org_pic,biz_pic,co_scale,co_desc," +
                "contact_person,contact_phone,contact_mail,co_site,co_pic,co_city,co_addr,check_code) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", new Object[]{uuid, cert_status, co_name, co_nickname,
                industry_type, co_type, org_code, biz_code, org_pic, biz_pic, co_scale, co_desc, contact_person, contact_phone,
                contact_mail, co_site, co_pic, co_city, co_addr, check_code});
        rConn.close();
    }

    private void delCompany(String user_id) throws InvalidParamsException, RelationalDatabaseException {
        RelationalDatabase rConn = new RelationalDatabase();
        if (user_id == null) {
            throw new InvalidParamsException();
        }
        rConn.doSQL("DELETE FROM company_inf WHERE uuid = ?", new Object[]{user_id});
        rConn.close();
    }

    private void modCompany(String user_id, int cert_status, String co_name, String co_nickname
            , int industry_type, int co_type, String org_code, String biz_code, String org_pic, String biz_pic, int co_scale
            , String co_desc, String contact_person, String contact_phone, String contact_mail, String co_site, String[] co_pic
            , int co_city, String co_addr, String check_code) throws RelationalDatabaseException, SQLException, UserNotExistException {
        RelationalDatabase rConn = new RelationalDatabase();
        ResultSet result = rConn.doQuery("SELECT uuid FROM company_inf WHERE co_name=?", new String[]{co_name});
        try {
            if (!result.first()) {
                rConn.close();
                throw new UserNotExistException();
            }
            if (co_name != "") {
                rConn.doSQL("UPDATE company_inf SET co_name = ? WHERE uuid=?", new Object[]{co_name, user_id});
                this.bCoName = true;
            }
            if (co_nickname != "") {
                rConn.doSQL("UPDATE company_inf SET co_nickname = ? WHERE uuid=?", new Object[]{co_nickname, user_id});
                this.bConickName = true;
            }
            if (industry_type <= 0) {
                rConn.doSQL("UPDATE company_inf SET industry_type = ? WHERE uuid=?", new Object[]{industry_type, user_id});
                this.bIndustryType = true;
            }
            if (co_type <= 0) {
                rConn.doSQL("UPDATE company_inf SET co_type = ? WHERE uuid=?", new Object[]{co_type, user_id});
                this.bCoType = true;
            }
            if (org_code != "") {
                rConn.doSQL("UPDATE company_inf SET org_code = ? WHERE uuid=?", new Object[]{org_code, user_id});
                this.bOrgCode = true;
            }
            if (biz_code != "") {
                rConn.doSQL("UPDATE company_inf SET biz_code = ? WHERE uuid=?", new Object[]{biz_code, user_id});
                this.bBizCode = true;
            }
            if (org_pic != "") {
                rConn.doSQL("UPDATE company_inf SET org_pic = ? WHERE uuid=?", new Object[]{org_pic, user_id});
                this.bOrgPic = true;
            }
            if (biz_pic != "") {
                rConn.doSQL("UPDATE company_inf SET biz_pic = ? WHERE uuid=?", new Object[]{biz_pic, user_id});
                this.bBizPic = true;
            }
            if (co_scale <= 0) {
                rConn.doSQL("UPDATE company_inf SET co_scale = ? WHERE uuid=?", new Object[]{co_scale, user_id});
                this.bCoScale = true;
            }
            if (co_desc != "") {
                rConn.doSQL("UPDATE company_inf SET co_desc = ? WHERE uuid=?", new Object[]{co_desc, user_id});
                this.bCoDesc = true;
            }
            if (contact_person != "") {
                rConn.doSQL("UPDATE company_inf SET contact_person = ? WHERE uuid=?", new Object[]{contact_person, user_id});
                this.bContactPerson = true;
            }
            if (contact_phone != "") {
                rConn.doSQL("UPDATE company_inf SET contact_phone = ? WHERE uuid=?", new Object[]{contact_phone, user_id});
                this.bContactPhone = true;
            }
            if (contact_mail != "") {
                rConn.doSQL("UPDATE company_inf SET contact_mail = ? WHERE uuid=?", new Object[]{contact_mail, user_id});
                this.bContactMail = true;
            }
            if (co_site != "") {
                rConn.doSQL("UPDATE company_inf SET co_site = ? WHERE uuid=?", new Object[]{co_site, user_id});
                this.bCoSite = true;
            }
            if (co_pic.length == 0) {
                String pic_token="";
                for (int i = 0; i < co_pic.length; i++) {
                    if(i!=0){
                        pic_token+=("|");
                    }
                    pic_token+=(co_pic[i]);
                }
                rConn.doSQL("UPDATE company_inf SET co_pic = ? WHERE uuid=?", new Object[]{pic_token, user_id});
                this.bCoPic = true;
            }
            if (co_city <= 0) {
                rConn.doSQL("UPDATE company_inf SET co_city = ? WHERE uuid=?", new Object[]{co_city, user_id});
                this.bCoCity = true;
            }
            if (co_addr != "") {
                rConn.doSQL("UPDATE company_inf SET co_nickname = ? WHERE uuid=?", new Object[]{co_nickname, user_id});
                this.bCoAddr = true;
            }

        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        rConn.close();
    }

    public boolean isbCoName() {
        return bCoName;
    }

    public boolean isbConickName() {
        return bConickName;
    }

    public boolean isbIndustryType() {
        return bIndustryType;
    }

    public boolean isbCoType() {
        return bCoType;
    }

    public boolean isbOrgCode() {
        return bOrgCode;
    }

    public boolean isbBizCode() {
        return bBizCode;
    }

    public boolean isbOrgPic() {
        return bOrgPic;
    }

    public boolean isbBizPic() {
        return bBizPic;
    }

    public boolean isbCoScale() {
        return bCoScale;
    }

    public boolean isbCoDesc() {
        return bCoDesc;
    }

    public boolean isbContactPerson() {
        return bContactPerson;
    }

    public boolean isbContactPhone() {
        return bContactPhone;
    }

    public boolean isbContactMail() {
        return bContactMail;
    }

    public boolean isbCoSite() {
        return bCoSite;
    }

    public boolean isbCoPic() {
        return bCoPic;
    }

    public boolean isbCoCity() {
        return bCoCity;
    }

    public boolean isbCoAddr() {
        return bCoAddr;
    }
}
