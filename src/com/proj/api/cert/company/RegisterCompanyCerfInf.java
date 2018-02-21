package com.proj.api.cert.company;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.InvalidBackstageOperationException;
import com.proj.api.exception.auth.InvalidOperationException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.cert.InvalidCertTypeException;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jangitlau on 2017/12/12.
 */
public class RegisterCompanyCerfInf {
    public RegisterCompanyCerfInf(String login_id, int cert_status, String co_name, String co_nickname, int industry_type, int co_type, String org_code, String biz_code, String org_pic, String biz_pic, int co_scale, String co_desc, String contact_person, String contact_phone, String contact_mail, String co_site, String[] co_pic_array, int co_city, String co_addr, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidOperationException, InvalidBackstageOperationException, InvalidCertTypeException, RelationalDatabaseException, InvalidCheckCodeException {
        AuthorizationUtils authorizationUtils=new AuthorizationUtils(login_id);
        authorizationUtils.checkParams(biz_code+biz_pic+cert_status+co_addr+co_city+co_desc+co_name+co_nickname+co_pic_array+co_scale+co_site+co_type+contact_mail+contact_person+contact_phone+industry_type+login_id+org_code+org_pic,check_code);

        if(authorizationUtils.getiType()!=2){
            throw new InvalidCertTypeException();
        }
        RelationalDatabase rConn = new RelationalDatabase();
        ResultSet rs;
        try{
            rs=rConn.doQuery("SELECT UUID FROM COMPANY_INF WHERE UUID=?",new String[]{authorizationUtils.getsUserId()});
            if(rs.first()){
                rConn.doSQL("DELETE FROM COMPANY_INF WHERE UUID=?",new String[]{authorizationUtils.getsUserId()});
            }
        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        String co_pic=rConn.ArrayToString(co_pic_array);
        rConn.doSQL("INSERT INTO COMPANY_INF (UUID,STATUS,CO_NAME,CO_NICKNAME," +
                "industry_type,co_type,org_code,biz_code,org_pic,biz_pic,co_scale,co_desc," +
                "contact_person,contact_phone,contact_mail,co_site,co_pic,co_city,co_addr,check_code) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", new Object[]{authorizationUtils.getsUserId(), cert_status, co_name, co_nickname,
                industry_type, co_type, org_code, biz_code, org_pic, biz_pic, co_scale, co_desc, contact_person, contact_phone,
                contact_mail, co_site, co_pic, co_city, co_addr, check_code});
        rConn.close();
    }
}
