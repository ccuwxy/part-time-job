package com.proj.api.permanentfile.serlvet;

import com.proj.api.FileInfo;
import com.proj.api.exception.file.CanNotHandlerFileException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.file.MongoDbException;
import com.proj.api.permanentfile.Gson.UploadFileRetGson;
import com.proj.api.permanentfile.controller.Download;
import com.proj.api.permanentfile.controller.Upload;
import com.proj.api.permanentfile.model.Files;
import com.proj.api.utils.Cache;
import com.proj.api.utils.InputStrUtils;
import com.proj.api.utils.DBdictionary;
import com.google.gson.Gson;
import com.proj.api.exception.database.NonRelationalDatabaseException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class PermanentFile extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retStr = "";
        byte[] retFile = null;
        String fileName = "";
        response.setHeader("content-type","text/html;charset=utf-8");
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String md5 = inputStrUtils.getRequiredParameter("file_token");
            Download download = new Download(md5);
            fileName = URLEncoder.encode(download.getFileName(), "utf-8"); //解决中文文件名下载后乱码的问题
            retFile = download.getFileByte();
        } catch (MongoDbException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        }
        try {
            if (retStr == "") {
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "");
                ServletOutputStream out = response.getOutputStream();
                out.write(retFile);
                out.flush();
                out.close();
            } else {
                response.getWriter().print(retStr);
            }
        } catch (Exception e) {
            response.getWriter().print(new CanNotHandlerFileException().getRetJson());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("doUpload1");
        String retStr = "";
        response.setHeader("content-type","text/html;charset=utf-8");
        try {
            ArrayList<FileInfo> fileInfos = Files.getFile(request);
            UploadFileRetGson uploadFileRetGson = new UploadFileRetGson();
            for (FileInfo fileInfo : fileInfos) {
                try {
                    //test
                    Cache cache = new Cache();

                    String user_id=cache.getID();
                    System.out.println(cache.getID());
                    String privage_user=cache.getID();
                    if (Integer.parseInt(cache.getAuthority())>5) {
                        privage_user="";
                    }
                    Upload upload = new Upload(fileInfo,user_id,privage_user);
                    uploadFileRetGson.addFileToken(upload.getsFileToken());
                    uploadFileRetGson.getCheck_code();
                } catch (ParseException ee) {
                    throw new CanNotHandlerFileException();
                } catch (NonRelationalDatabaseException e) {
                    e.printStackTrace();
                }
            }
            retStr = new Gson().toJson(uploadFileRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (CanNotHandlerFileException e) {
            retStr = e.getRetJson();
        }
        response.getWriter().print(retStr);
    }
}
