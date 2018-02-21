package com.proj.api.cert.company;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
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
public class DeleteCompanyCerfInf {
    public DeleteCompanyCerfInf(String _sLoginId,String _sUserId,String _sCheckCode) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidOperationException, InvalidBackstageOperationException, InvalidCheckCodeException, RelationalDatabaseException, InvalidCertTypeException, UserNotExistException {
        AuthorizationUtils authorizationUtils=new AuthorizationUtils(_sLoginId);
        authorizationUtils.checkParams(_sLoginId+_sUserId,_sCheckCode);

        if(authorizationUtils.getiType()!=3){
            throw new InvalidOperationException();
        }
        if(authorizationUtils.getiAuthority()<5){
            throw new InvalidBackstageOperationException();
        }

        RelationalDatabase rConn=new RelationalDatabase();
        ResultSet rs;
        try{
            rs=rConn.doQuery("SELECT UUID,TYPE FROM COMPANY_INF WHERE UUID=?",new String[]{_sUserId});
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
            rs=rConn.doQuery("SELECT UUID FROM COMPANY_INF WHERE UUID=?",new String[]{_sUserId});
            if(rs.first()){
                rConn.doSQL("DELETE FROM COMPANY_INF WHERE UUID=?",new String[]{_sUserId});
            }
            rConn.doSQL("UPDATE USER_AUTH SET TYPE=1 WHERE UUID=?",new String[]{_sUserId});
        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        rConn.close();
    }
}
