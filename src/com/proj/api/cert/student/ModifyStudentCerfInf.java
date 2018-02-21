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


public class ModifyStudentCerfInf {
    public ModifyStudentCerfInf(String login_id, String user_id, int cert_status, String stu_name, int stu_school, String stu_id, String stu_pwd, String id_code, String id_front_pic, String id_back_pic, int stu_college, int stu_major, String stu_adms_year, String stu_grad_year, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, RelationalDatabaseException, UserNotExistException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(login_id);
        authorizationUtils.checkParams(cert_status + id_back_pic + id_code + id_front_pic + login_id + stu_adms_year + stu_college + stu_grad_year + stu_id + stu_major + stu_name + stu_pwd + stu_school + user_id, check_code);

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
            relationalDatabase.doSQL("update student set cert_status=?,id_back_pic=?,id_code=?,id_front_pic=?,stu_adms_year=?,stu_college=?,stu_grad_year=?,stu_id=?,stu_major=?,stu_name=?stu_pwd=?stu_school=? where uuid=?", new Object[]{cert_status, id_back_pic, id_code, id_front_pic, stu_adms_year, stu_college, stu_grad_year, stu_id, stu_major, stu_name, stu_pwd, stu_school, user_id});
        } catch (SQLException e) {
            relationalDatabase.close();
            throw new RelationalDatabaseException(e);
        }
        relationalDatabase.close();
        this.user_id = user_id;
        this.check_code = authorizationUtils.getCheckCode(login_id);
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
