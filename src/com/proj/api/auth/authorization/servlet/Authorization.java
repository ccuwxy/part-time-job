package com.proj.api.auth.authorization.servlet;

import com.proj.api.auth.authorization.controller.PreAuthorization;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.auth.InvalidOperationException;
import com.proj.api.exception.auth.PasswordNotCorrectException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotExistException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.auth.authorization.gson.AuthorizationRecvGson;
import com.proj.api.auth.authorization.gson.AuthorizationRetGson;
import com.proj.api.auth.authorization.gson.PreAuthorizationRetGson;
import com.proj.api.utils.InputStrUtils;
import com.proj.api.utils.JsonUtils;

import java.io.IOException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Authorization extends javax.servlet.http.HttpServlet {

    // 注册
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils=new InputStrUtils(request);
            String recvStr = inputStrUtils.getRecvString();
            AuthorizationRecvGson authorizationRecvGson = JsonUtils.fromJson(recvStr, AuthorizationRecvGson.class);
            com.proj.api.auth.authorization.controller.Authorization authorization = new com.proj.api.auth.authorization.controller.Authorization(
                    authorizationRecvGson.getUsername(),
                    authorizationRecvGson.getRand_str(),
                    authorizationRecvGson.getPre_password());
            AuthorizationRetGson authorizationRetGson = new AuthorizationRetGson(
                    authorization.getsLoginId(),
                    authorization.getsUserId(),
                    authorization.getsUsername(),
                    authorization.getsPhoneNum(),
                    authorization.getiType(),
                    authorization.getiAuthority(),
                    authorization.getiStatus(),
                    authorization.getsPreToken(),
                    authorization.getiExpire()
            );
            retStr = JsonUtils.toJson(authorizationRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (AESEncryptException e) {
            retStr = e.getRetJson();
        } catch (PasswordNotCorrectException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

    // 预注册
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils=new InputStrUtils(request);
            String sUsername = inputStrUtils.getRequiredParameter("username");
            if (sUsername == null) {
                throw new InvalidParamsException();
            }
            PreAuthorization preAuthorization = new PreAuthorization(sUsername);
            PreAuthorizationRetGson preAuthorizationRetGson = new PreAuthorizationRetGson(
                    preAuthorization.getsUsername()
                    , preAuthorization.getsKey()
            );
            retStr = JsonUtils.toJson(preAuthorizationRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserNotExistException e) {
            retStr = e.getRetJson();
        } catch (AESEncryptException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }

}