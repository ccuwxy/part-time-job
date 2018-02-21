package com.proj.api.auth.info.servlet;

import com.proj.api.auth.info.gson.ModifyPasswordRecvGson;
import com.proj.api.auth.info.gson.ModifyPasswordRetGson;
import com.proj.api.exception.auth.UserDisableException;
import com.proj.api.exception.auth.UserNotAuthorizedException;
import com.proj.api.exception.auth.UserNotExistException;
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
 * Created by jangitlau on 2017/12/6.
 */
public class ModifyPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr="";
        try{
            InputStrUtils inputStrUtils=new InputStrUtils(request);
            ModifyPasswordRecvGson modifyPasswordRecvGson= JsonUtils.fromJson(inputStrUtils.getRecvString(),ModifyPasswordRecvGson.class);
            com.proj.api.auth.info.controller.ModifyPassword modifyPassword=new com.proj.api.auth.info.controller.ModifyPassword(
                    modifyPasswordRecvGson.getUser_id(),
                    modifyPasswordRecvGson.getNew_password(),
                    modifyPasswordRecvGson.getCheck_code()
            );
            retStr=JsonUtils.toJson(new ModifyPasswordRetGson());
        } catch (MalformedJsonException e) {
            retStr=e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr=e.getRetJson();
        } catch (InvalidCheckCodeException e) {
            retStr=e.getRetJson();
        } catch (AESDecryptException e) {
            retStr=e.getRetJson();
        } catch (UserDisableException e) {
            retStr=e.getRetJson();
        } catch (UserNotExistException e) {
            retStr=e.getRetJson();
        } catch (UserNotAuthorizedException e) {
            retStr=e.getRetJson();
        } catch (RelationalDatabaseException e) {
            retStr=e.getRetJson();
        } catch (NonRelationalDatabaseException e) {
            retStr=e.getRetJson();
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getWriter().print(retStr);
    }
}
