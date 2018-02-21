package com.proj.api.temporaryfile.serlvet;

import com.proj.api.FileInfo;
import com.proj.api.exception.file.CanNotHandlerFileException;
import com.proj.api.exception.other.InvalidParamsException;
import com.proj.api.exception.file.MongoDbException;
import com.proj.api.temporaryfile.Gson.UploadFileRetGson;
import com.proj.api.temporaryfile.controller.Download;
import com.proj.api.temporaryfile.controller.Upload;
import com.proj.api.temporaryfile.model.Files;
import com.proj.api.utils.InputStrUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;

public class TemporaryFile extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        String retStr = "";
        byte[] retFile = null;
        String fileName = "";
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

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //System.out.println("doUpload1");
        String retStr = "";
        try {
            ArrayList<FileInfo> fileInfos = Files.getFile(request);
            UploadFileRetGson uploadFileRetGson = new UploadFileRetGson();
            for (FileInfo fileInfo : fileInfos) {
                try {
                    Upload upload = new Upload(fileInfo);
                    uploadFileRetGson.addFileToken(upload.getsFileToken());
                    uploadFileRetGson.getCheck_code();
                } catch (ParseException ee) {
                    throw new CanNotHandlerFileException();
                }
            }
            retStr = new Gson().toJson(uploadFileRetGson);
        } catch (CanNotHandlerFileException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        }
        response.getWriter().print(retStr);
    }
}
