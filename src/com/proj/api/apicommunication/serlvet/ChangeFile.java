package com.proj.api.apicommunication.serlvet;

import com.proj.api.apicommunication.Gson.ChangeFileRetGson;
import com.proj.api.apicommunication.controller.DeletePerFILE;
import com.proj.api.exception.file.CanNotHandlerFileException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.file.MongoDbException;
import com.proj.api.apicommunication.controller.TemToPer;
import com.proj.api.utils.InputStrUtils;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ChangeFile")
public class ChangeFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        byte[] retFile = null;
        String fileName = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String md5 = inputStrUtils.getRequiredParameter("file_token");
            //test
            String privilege_user="";
            String user_id="";
            String check_code = DigestUtils.md5Hex(privilege_user+user_id+md5);
            DeletePerFILE delete = new DeletePerFILE(md5,privilege_user,user_id,check_code);
            ChangeFileRetGson changeFileRetGson = new ChangeFileRetGson();
            //changeFileRetGson.addFileToken(md5);
            retStr = new Gson().toJson(changeFileRetGson);
        } catch (MongoDbException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        }
        response.setHeader("content-type","text/html;charset=utf-8");
        response.getWriter().print(retStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        byte[] retFile = null;
        String fileName = "";
        response.setHeader("content-type","text/html;charset=utf-8");
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);

            ChangeFileRetGson changeFileRetGson = new ChangeFileRetGson();
            String md5 = inputStrUtils.getRequiredParameter("file_token");
            String check_code = DigestUtils.md5Hex(changeFileRetGson.getErr_code()+changeFileRetGson.getReason()+md5);
            String privilege_user = "";
            TemToPer TToP = new TemToPer(md5,privilege_user,check_code);
            //
            // changeFileRetGson.addFileToken(md5);
            retStr = new Gson().toJson(changeFileRetGson);
        } catch (MongoDbException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (CanNotHandlerFileException e){
            retStr = e.getRetJson();
        }
        try {
            response.getWriter().print(retStr);
        } catch (Exception e) {
            response.getWriter().print(new CanNotHandlerFileException().getRetJson());
        }
    }
}
