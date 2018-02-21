package com.proj.api.temporaryfile.model;

import com.proj.api.exception.file.CanNotHandlerFileException;
import com.proj.api.exception.other.InvalidParamsException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.proj.api.FileInfo;

import java.io.ByteArrayOutputStream;

public class Files extends HttpServlet{
    //private static final String UPLOAD_DIRECTORY = "file";
    public  static ArrayList<FileInfo> getFile(javax.servlet.http.HttpServletRequest request) throws InvalidParamsException, CanNotHandlerFileException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new InvalidParamsException();
        }
        //System.out.println("file1");
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(16 * 1024 * 1024);
        factory.setRepository(new java.io.File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(16 * 1024 * 1024);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(16 * 1024 * 1024);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 这个路径相对当前应用的目录
        //String uploadPath = getServletContext().getRealPath("./") + java.io.File.separator + UPLOAD_DIRECTORY;
        ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] file = new byte[0];
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            //System.out.println("file2");
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        long fileSize = item.getSize();
                        if (fileSize > 16 * 1024 * 1024 || fileSize == 0) {
                            continue;
                        }
                        String fileName = new File(item.getName()).getName();
                        //System.out.println(fileName);
                        file = item.get();
                        //System.out.println("file3");

                        System.out.println(fileName);
                        fileList.add(new FileInfo(file, fileSize, fileName));
                        //System.out.println("file4");
                    }
                }
            }
        } catch (Exception ex) {
            throw new CanNotHandlerFileException();
        }

        return fileList;
    }

}
