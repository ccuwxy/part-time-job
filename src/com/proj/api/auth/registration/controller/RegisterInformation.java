package com.proj.api.auth.registration.controller;
/**
 * author:Jerry
 */
import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.utils.MalformedJsonException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterInformation {
    public RegisterInformation(String _sInput) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, RelationalDatabaseException, UserAlreadyExistException, PhoneAlreadyExistException {
        RelationalDatabase rConn = new RelationalDatabase();
        ResultSet result;
        if (_sInput != null && _sInput != "") {
            try {
                result = rConn.doQuery("SELECT uuid FROM user_auth WHERE username=?", new String[]{_sInput});
                if (result.first()) {
                    rConn.close();
                    throw new UserAlreadyExistException();
                }

                result = rConn.doQuery("SELECT uuid FROM user_auth WHERE phone_num=?", new String[]{_sInput});
                if (result.first()) {
                    rConn.close();
                    throw new PhoneAlreadyExistException();
                }
            } catch (SQLException e) {
                rConn.close();
                throw new RelationalDatabaseException(e);
            }
        }
        rConn.close();
    }

}
