package com.proj.api.cert.student;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DeleteStudentCerfInf {
    public DeleteStudentCerfInf(String login_id, String user_id, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, RelationalDatabaseException, UserNotExistException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(login_id);
        authorizationUtils.checkParams(login_id + user_id, check_code);

        if (authorizationUtils.getiType() != 3) {
            throw new InvalidOperationException();
        }
        if (authorizationUtils.getiAuthority() < 5) {
            throw new InvalidBackstageOperationException();
        }

        RelationalDatabase relationalDatabase = new RelationalDatabase();
        ResultSet resultSet;
        resultSet = relationalDatabase.doQuery("select uuid from student_inf where uuid=?", new Object[]{user_id});
        try {
            if (!resultSet.first()) {
                relationalDatabase.close();
                throw new UserNotExistException();
            }
            relationalDatabase.doSQL("delete from studet_inf where uuid=?", new Object[]{user_id});
        } catch (SQLException e) {
            relationalDatabase.close();
            throw new RelationalDatabaseException(e);
        }
        relationalDatabase.close();
        this.user_id = user_id;
        this.check_code = authorizationUtils.getCheckCode(user_id);
    }

    private String user_id;
    private String check_code;

    public String getUser_id() {
        return user_id;
    }

    public String getCheck_code() {
        return check_code;
    }
}
