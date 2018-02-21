package com.proj.api.cert.servlet;

import com.proj.api.cert.company.RegisterCompanyCerfInf;
import com.proj.api.cert.controller.ModifyCompanyInfo;
import com.proj.api.cert.gson.ModifyCompanyRecvGson;
import com.proj.api.cert.gson.ModifyCompanyRetGson;
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
import java.sql.SQLException;


public class CertificationDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        InputStrUtils inputStrUtils = new InputStrUtils(request);
        //RegisterCompanyCerfInf registerCompanyCerfInf = new RegisterCompanyCerfInf();
        /*try {


            ModifyCompanyRecvGson modifyCompanyRecvGson = JsonUtils.fromJson(inputStrUtils.getRecvString(), ModifyCompanyRecvGson.class);
            ModifyCompanyInfo modifyCompanyInfo = new ModifyCompanyInfo(
                    modifyCompanyRecvGson.getAction(),
                    modifyCompanyRecvGson.getAuth_id(),
                    modifyCompanyRecvGson.getUser_id(),
                    modifyCompanyRecvGson.getCert_status(),
                    modifyCompanyRecvGson.getCo_name(),
                    modifyCompanyRecvGson.getCo_nickname(),
                    modifyCompanyRecvGson.getIndustry_type(),
                    modifyCompanyRecvGson.getCo_type(),
                    modifyCompanyRecvGson.getOrg_code(),
                    modifyCompanyRecvGson.getBiz_code(),
                    modifyCompanyRecvGson.getOrg_pic(),
                    modifyCompanyRecvGson.getBiz_pic(),
                    modifyCompanyRecvGson.getCo_scale(),
                    modifyCompanyRecvGson.getCo_desc(),
                    modifyCompanyRecvGson.getContact_person(),
                    modifyCompanyRecvGson.getContact_phone(),
                    modifyCompanyRecvGson.getContact_mail(),
                    modifyCompanyRecvGson.getCo_site(),
                    modifyCompanyRecvGson.getCo_pic(),
                    modifyCompanyRecvGson.getCo_city(),
                    modifyCompanyRecvGson.getCo_addr(),
                    modifyCompanyRecvGson.getCheck_code()
            );
            ModifyCompanyRetGson modifyCompanyRetGson = new ModifyCompanyRetGson(
                    modifyCompanyInfo.isbCoName(),
                    modifyCompanyInfo.isbConickName(),
                    modifyCompanyInfo.isbIndustryType(),
                    modifyCompanyInfo.isbCoType(),
                    modifyCompanyInfo.isbOrgCode(),
                    modifyCompanyInfo.isbBizCode(),
                    modifyCompanyInfo.isbOrgPic(),
                    modifyCompanyInfo.isbBizPic(),
                    modifyCompanyInfo.isbCoScale(),
                    modifyCompanyInfo.isbCoDesc(),
                    modifyCompanyInfo.isbContactPerson(),
                    modifyCompanyInfo.isbContactPhone(),
                    modifyCompanyInfo.isbContactMail(),
                    modifyCompanyInfo.isbCoSite(),
                    modifyCompanyInfo.isbCoPic(),
                    modifyCompanyInfo.isbCoCity(),
                    modifyCompanyInfo.isbCoAddr()

            );

        } catch (MalformedJsonException e) {
            e.printStackTrace();
        } catch (InvalidParamsException e) {
            e.printStackTrace();
        } catch (UserNotAuthorizedException e) {
            e.printStackTrace();
        } catch (UserNotExistException e) {
            e.printStackTrace();
        } catch (NonRelationalDatabaseException e) {
            e.printStackTrace();
        } catch (InvalidBackstageOperationException e) {
            e.printStackTrace();
        } catch (UserDisableException e) {
            e.printStackTrace();
        } catch (RelationalDatabaseException e) {
            e.printStackTrace();
        } catch (InvalidCheckCodeException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
