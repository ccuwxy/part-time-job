package com.proj.api.cert.company;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.cert.InvalidCertTypeException;
import com.proj.api.exception.cert.UserCertAlreadyExistException;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author:Jerry
 */
public class ModifyCompanyCerfInf {
    public ModifyCompanyCerfInf(String login_id,String user_id,int cert_status,String co_name,String co_nickname,int industry_type,int co_type,
    String org_code,int biz_code,String org_pic,String biz_pic,int co_scale,String co_desc,String contact_person,String contact_phone,String contact_mail,
    String co_site,String[] co_pic_array,int co_city,int co_addr,String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, RelationalDatabaseException, InvalidCertTypeException, UserNotExistException, UserCertAlreadyExistException {
        AuthorizationUtils authorizationUtils=new AuthorizationUtils(login_id);
        authorizationUtils.checkParams(biz_code+biz_pic+cert_status+co_addr+co_city+co_desc+co_name+co_nickname+co_pic_array+co_scale+co_site+co_type+contact_mail+contact_person+contact_phone+industry_type+login_id+org_code+org_pic+user_id,check_code);

            if(authorizationUtils.getiType()!=3){
                throw new InvalidOperationException();
            }
            if(authorizationUtils.getiAuthority()<5){
                throw new InvalidBackstageOperationException();
            }

        RelationalDatabase rConn = new RelationalDatabase();

        ResultSet rs;
        try{
            rs=rConn.doQuery("SELECT UUID,TYPE FROM USER_AUTH WHERE UUID=?",new String[]{user_id});
            if(rs.first()){
                int iType=rs.getInt("TYPE");
                if(iType!=2){
                    rConn.close();
                    throw new InvalidCertTypeException();
                }
            }else{
                rConn.close();
                throw new UserNotExistException();
            }
            rs=rConn.doQuery("SELECT UUID FROM COMPANY_INF WHERE UUID=?",new String[]{user_id});
            if(rs.first()){
                rConn.close();
                throw new UserCertAlreadyExistException();
            }
        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        String co_pic=rConn.ArrayToString(co_pic_array);
        rConn.doSQL("UPDATE INTO COMPANY_INF SET cert_status=?,co_name=?, co_nickname=?,industry_type=?,co_type=?,org_code=?,biz_code=?"+
                "org_pic=?,biz_pic=?,co_scale=?,co_desc=?,contact_person=?,contact_phone=?,contact_mail=?,co_site=?,co_pic=?,co_city=?,co_addr=?,check_code=?",
                new Object[]{cert_status,co_name,co_nickname,industry_type,co_type,org_code,biz_code,org_pic,biz_pic,co_scale,co_desc,contact_person,contact_phone,contact_mail,
                co_site,co_pic,co_city,co_addr,check_code});
        rConn.doSQL("UPDATE USER_AUTH SET STATUS=2 WHERE UUID=?",new String[]{user_id});
        rConn.close();
    }
}
