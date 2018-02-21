package com.proj.api.auth.info.servlet;

import com.proj.api.auth.info.gson.UserInfoDetailRetGson;
import com.proj.api.exception.auth.*;
import com.proj.api.exception.database.NonRelationalDatabaseException;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.exception.other.InvalidCheckCodeException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.utils.MalformedJsonException;
import com.proj.api.utils.InputStrUtils;
import com.proj.api.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangitlau on 2017/12/6.
 */
public class UserInfoDetail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
//            String sUserId = inputStrUtils.getPathInfo();
            String sLoginId = inputStrUtils.getRequiredParameter("login_id");
            String sCheckCode = inputStrUtils.getRequiredParameter("check_code");
            com.proj.api.auth.info.controller.UserInfoDetail userInfoDetail = new com.proj.api.auth.info.controller.UserInfoDetail(
                    sLoginId, sCheckCode
            );
            UserInfoDetailRetGson userInfoDetailRetGson = new UserInfoDetailRetGson(
                    userInfoDetail.getsUserId(),
                    userInfoDetail.getsUserName(),
                    userInfoDetail.getsPhoneNum(),
                    userInfoDetail.getiType(),
                    userInfoDetail.getiAuthority(),
                    userInfoDetail.getiStatus(),
                    userInfoDetail.getsCheckCode()
            );
            retStr = JsonUtils.toJson(userInfoDetailRetGson);
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
}
