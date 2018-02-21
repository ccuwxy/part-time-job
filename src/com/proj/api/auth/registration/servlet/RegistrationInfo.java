package com.proj.api.auth.registration.servlet;
/**
 * author:Jerry
 */
import com.proj.api.exception.auth.PhoneAlreadyExistException;
import com.proj.api.exception.auth.UserAlreadyExistException;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.auth.registration.controller.RegisterInformation;
import com.proj.api.auth.registration.gson.RegisterInformationRetGson;
import com.proj.api.utils.InputStrUtils;
import com.proj.api.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils=new InputStrUtils(request);
            String sPath = inputStrUtils.getPathInfo();
            RegisterInformation registerInformation = new RegisterInformation(sPath);
            retStr = JsonUtils.toJson(new RegisterInformationRetGson());
        } catch (UserNotAuthorizedException e) {
            retStr = e.getRetJson();
        } catch (UserDisableException e) {
            retStr = e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (MalformedJsonException e) {
            retStr = e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr = e.getRetJson();
        } catch (PhoneAlreadyExistException e) {
            retStr = e.getRetJson();
        } catch (UserAlreadyExistException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }
}
