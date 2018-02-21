package com.proj.api.auth.registration.servlet;

import com.google.gson.Gson;
import com.proj.api.auth.registration.gson.PreRegistrationRetGson;
import com.proj.api.auth.registration.gson.RegistrationRecvGson;
import com.proj.api.auth.registration.gson.RegistrationRetGson;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.auth.InvalidOperationException;
import com.proj.api.exception.auth.PhoneAlreadyExistException;
import com.proj.api.exception.auth.UserAlreadyExistException;
import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.AESEncryptException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.auth.registration.controller.PreRegistration;
import com.proj.api.utils.InputStrUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson json = new Gson();
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String recvStr = inputStrUtils.getRecvString();
            RegistrationRecvGson registrationRecvGson = json.fromJson(recvStr, RegistrationRecvGson.class);
            com.proj.api.auth.registration.controller.Registration registration = new com.proj.api.auth.registration.controller.Registration(
                    registrationRecvGson.getUsername()
                    , registrationRecvGson.getPhone_num()
                    , registrationRecvGson.getPassword_key()
                    , registrationRecvGson.getType());
            RegistrationRetGson registrationRetGson = new RegistrationRetGson(
                    registration.getsLoginId(),
                    registration.getsUserId(),
                    registration.getsUsername(),
                    registration.getsPhoneNum(),
                    registration.getiType(),
                    registration.getiAuthority(),
                    registration.getiStatus(),
                    registration.getsPreToken(),
                    registration.getiExpire());
            retStr = json.toJson(registrationRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (AESDecryptException e) {
            retStr = e.getRetJson();
        } catch (AESEncryptException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (InvalidOperationException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        Gson json = new Gson();
        try {
            InputStrUtils inputStrUtils=new InputStrUtils(request);
            String sPhoneNum = inputStrUtils.getRequiredParameter("phone_num");
            if (sPhoneNum == null) {
                throw new InvalidParamsException();
            }
            PreRegistration preRegistration = new PreRegistration(sPhoneNum);
            PreRegistrationRetGson preRegistrationRetGson = new PreRegistrationRetGson(
                    preRegistration.getsPhoneNum(), preRegistration.getsKey());
            retStr = json.toJson(preRegistrationRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (UserAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (AESEncryptException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (PhoneAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }
}
