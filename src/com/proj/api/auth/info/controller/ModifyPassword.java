package com.proj.api.auth.info.controller;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.auth.UserNotExistException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.EncryptUtils;
import com.proj.api.utils.SensitiveDataUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyPassword {
    public ModifyPassword(String _sUserId,String _sExchangePassword,String _sCheckCode) throws NonRelationalDatabaseException, UserNotAuthorizedException, InvalidCheckCodeException, RelationalDatabaseException, UserNotExistException, AESDecryptException, MalformedJsonException, UserDisableException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(_sUserId);
        authorizationUtils.checkParams(_sExchangePassword + _sUserId, _sCheckCode);

        //get old tran pwd
        RelationalDatabase rConn=new RelationalDatabase();
        ResultSet rs=rConn.doQuery("SELECT tran_password FROM user_auth WHERE uuid=?",new String[]{_sUserId});
        String sOldTranPassword="";
        try{
            if(rs.first()){
                sOldTranPassword=rs.getString("tran_password");
            }else{
                rConn.close();
                throw new UserNotExistException();
            }
        } catch (SQLException e) {
            rConn.close();
            throw new RelationalDatabaseException(e);
        }
        String sClearPassword= EncryptUtils.AES.decryptData(_sExchangePassword, sOldTranPassword + authorizationUtils.getsToken());
        String sTranPassword = DigestUtils.md5Hex(sClearPassword + SensitiveDataUtils.sTranPasswordSalt);
        String sAuthPassword = DigestUtils.md5Hex(
                DigestUtils.md5Hex(sClearPassword + SensitiveDataUtils.sPrePasswordSalt)
                        + SensitiveDataUtils.sAuthPasswordSalt);
        rConn.doSQL("UPDATE user_auth SET auth_password=?,tran_password=? WHERE uuid=?",new String[]{sAuthPassword,sTranPassword,_sUserId});
        rConn.close();
    }
}
