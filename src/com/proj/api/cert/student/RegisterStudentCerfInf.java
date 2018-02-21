package com.proj.api.cert.student;

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
public class RegisterStudentCerfInf {
    public RegisterStudentCerfInf(String login_id, String user_id, String stu_name, int stu_school, String stu_id, String stu_pwd, String id_code, String id_front_pic, String id_back_pic, int stu_college, int stu_major, String stu_adms_year, String stu_grad_year, String check_code) throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, InvalidCertTypeException, RelationalDatabaseException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(login_id);
        authorizationUtils.checkParams(id_back_pic + id_code + id_front_pic + login_id + stu_adms_year + stu_college + stu_grad_year + stu_id + stu_major + stu_name + stu_pwd + stu_school + user_id, check_code);

        if (authorizationUtils.getiType() != 2) {
            throw new InvalidCertTypeException();
        }

        RelationalDatabase relationalDatabase = new RelationalDatabase();
        ResultSet resultSet;
        resultSet = relationalDatabase.doQuery("select uuid from student_inf where uuid=?", new Object[]{user_id});
        try {
            if (resultSet.first()) {
                relationalDatabase.doSQL("delete from student_inf where uuid=?", new Object[]{user_id});
            }
        } catch (SQLException e) {
            relationalDatabase.close();
            throw new RelationalDatabaseException(e);
        }
        relationalDatabase.doSQL("insert into student_inf (id_back_pic,id_code,id_front_pic,login_id,stu_adms_year,stu_college,stu_grad_year,stu_id,stu_major,stu_name,stu_pwd,stu_school,user_id",
                new Object[]{id_back_pic, id_code, id_front_pic, login_id, stu_adms_year, stu_college, stu_grad_year, stu_id, stu_major, stu_name, stu_pwd, stu_school, user_id});
        relationalDatabase.close();
    }
}
