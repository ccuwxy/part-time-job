package com.proj.api.cert.controller;

import com.proj.api.database.RelationalDatabase;
import com.proj.api.exception.auth.InvalidBackstageOperationException;
import com.proj.api.exception.auth.InvalidOperationException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.AuthorizationUtils;
import com.proj.api.utils.RandomUtils;

public class ModifyStudentInfo {
    public ModifyStudentInfo(String action, String auth_id, String user_id, int cert_status, String stu_name,
           int stu_school, String stu_id, String stu_pwd, String id_code, String id_front_pic, String id_back_pic,
           int stu_college, int stu_major, String stu_adms_year, String stu_grad_year, String check_code)
            throws UserNotAuthorizedException, UserDisableException, NonRelationalDatabaseException, MalformedJsonException, InvalidCheckCodeException, InvalidOperationException, InvalidBackstageOperationException, InvalidParamsException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(auth_id);
        authorizationUtils.checkParams(action+auth_id+cert_status+id_back_pic+id_code+id_front_pic+stu_adms_year+stu_college+stu_grad_year+
                stu_id+stu_major+stu_name+stu_pwd+stu_school+user_id,check_code);
        if (authorizationUtils.getiType() != 3) {
            throw new InvalidOperationException();
        }
        if(authorizationUtils.getiAuthority() < 5){
            throw new InvalidBackstageOperationException();
        }
        switch (action) {
            case "add":
               // addStudent();
                break;
            case "del":
                delStudent(user_id);
                break;
            case "mod":
                modStudent();
                break;
            default:
                throw new InvalidParamsException();
        }
    }
    private void addStudent(String action,int auth_id,int user_id,int cert_status,String stu_name,String stu_school,String stu_id,String stu_pwd,String id_code,String id_front_pic
    ,String id_back_pic,int stu_college,int stu_major,String stu_adms_year,String stu_grad_year,String check_code)
            throws RelationalDatabaseException {
        RelationalDatabase rConn = new RelationalDatabase();
        String uuid = RandomUtils.getUUID();
        //rConn.doSQL();
    }
    private void delStudent(String user_id){

    }
    private void modStudent(){

    }
}
