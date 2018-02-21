package com.proj.api.auth.backstage.servlet;

import com.proj.api.auth.backstage.controller.AddUserInfo;
import com.proj.api.auth.backstage.controller.DeleteUserInfo;
import com.proj.api.auth.backstage.controller.GetUserInfo;
import com.proj.api.auth.backstage.controller.ModifyUserInfo;
import com.proj.api.auth.backstage.gson.AddUserInfoRecvGson;
import com.proj.api.auth.backstage.gson.BackgroundInfoDetailRetGson;
import com.proj.api.auth.backstage.gson.GetUserInfoRetGson;
import com.proj.api.auth.backstage.gson.ModifyUserInfoRecvGson;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.InputStrUtils;
import com.proj.api.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangitlau on 2017/12/7.
 */
public class BackgroundInfoDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //增加用户
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            AddUserInfoRecvGson addUserInfoRecvGson = JsonUtils.fromJson(inputStrUtils.getRecvString(), AddUserInfoRecvGson.class);
            AddUserInfo addUserInfo = new AddUserInfo(
                    addUserInfoRecvGson.getLogin_id(),
                    addUserInfoRecvGson.getUsername(),
                    addUserInfoRecvGson.getPhone_num(),
                    addUserInfoRecvGson.getPassword_key(),
                    addUserInfoRecvGson.getType(),
                    addUserInfoRecvGson.getAuthority(),
                    addUserInfoRecvGson.getStatus(),
                    addUserInfoRecvGson.getCheck_code()
            );
            BackgroundInfoDetailRetGson backgroundInfoDetailRetGson = new BackgroundInfoDetailRetGson(
                    addUserInfo.getsUserId(),
                    addUserInfo.getsCheckCode()
            );
            retStr = JsonUtils.toJson(backgroundInfoDetailRetGson);
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        } catch (UserNotAuthorizedException e) {
            retStr = e.getRetJson();
        } catch (PhoneAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidBackstageOperationException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        } catch (UserAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (InvalidCheckCodeException e) {
            retStr = e.getRetJson();
        } catch (AESDecryptException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除用户
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String sLoginId = inputStrUtils.getRequiredParameter("login_id");
            String sUserId = inputStrUtils.getRequiredParameter("user_id");
            String sCheckCode = inputStrUtils.getRequiredParameter("check_code");

            DeleteUserInfo deleteUserInfo = new DeleteUserInfo(sLoginId, sUserId, sCheckCode);

            BackgroundInfoDetailRetGson backgroundInfoDetailRetGson = new BackgroundInfoDetailRetGson(
                    deleteUserInfo.getsUserId(),
                    deleteUserInfo.getsCheckCode()
            );
            retStr = JsonUtils.toJson(backgroundInfoDetailRetGson);
        } catch (UserNotExistException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        } catch (UserNotAuthorizedException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidBackstageOperationException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (InvalidCheckCodeException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String sLoginId = inputStrUtils.getRequiredParameter("login_id");
            String sUserId = inputStrUtils.getRequiredParameter("user_id");
            String sCheckCode = inputStrUtils.getRequiredParameter("check_code");

            GetUserInfo getUserInfo=new GetUserInfo(sLoginId,sUserId,sCheckCode);

            GetUserInfoRetGson getUserInfoRetGson=new GetUserInfoRetGson(
                    getUserInfo.getUser_id(),
                    getUserInfo.getUsername(),
                    getUserInfo.getPhone_num(),
                    getUserInfo.getType(),
                    getUserInfo.getAuthority(),
                    getUserInfo.getStatus(),
                    getUserInfo.getCheck_code()
            );
            retStr=JsonUtils.toJson(getUserInfoRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserNotAuthorizedException e) {
            retStr = e.getRetJson();
        } catch (UserNotExistException e) {
            retStr = e.getRetJson();
        } catch (InvalidBackstageOperationException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidCheckCodeException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            ModifyUserInfoRecvGson modifyUserInfoRecvGson = JsonUtils.fromJson(inputStrUtils.getRecvString(), ModifyUserInfoRecvGson.class);

            ModifyUserInfo modifyUserInfo = new ModifyUserInfo(
                    modifyUserInfoRecvGson.getLogin_id(),
                    modifyUserInfoRecvGson.getUser_id(),
                    modifyUserInfoRecvGson.getUsername(),
                    modifyUserInfoRecvGson.getPhone_num(),
                    modifyUserInfoRecvGson.getPassword_key(),
                    modifyUserInfoRecvGson.getType(),
                    modifyUserInfoRecvGson.getAuthority(),
                    modifyUserInfoRecvGson.getStatus(),
                    modifyUserInfoRecvGson.getCheck_code()
            );

            BackgroundInfoDetailRetGson backgroundInfoDetailRetGson = new BackgroundInfoDetailRetGson(
                    modifyUserInfo.getsUserId(),
                    modifyUserInfo.getsCheckCode()
            );
            retStr = JsonUtils.toJson(backgroundInfoDetailRetGson);
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserNotAuthorizedException e) {
            retStr = e.getRetJson();
        } catch (AESDecryptException e) {
            retStr = e.getRetJson();
        } catch (InvalidBackstageOperationException e) {
            retStr = e.getRetJson();
        } catch (UserNotExistException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidCheckCodeException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }
}
