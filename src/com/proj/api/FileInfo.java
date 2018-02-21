package com.proj.api;

import org.apache.commons.codec.digest.DigestUtils;

public class FileInfo {
    private byte[] file_byte;
    private long file_size;
    private String orginFileName;
    private String md5FileName;
    private long createTime;

    public FileInfo(byte[] file_byte, long file_size, String orginFileName) {
        this.file_byte = file_byte;
        this.file_size = file_size;
        this.orginFileName = orginFileName;
        this.md5FileName = DigestUtils.md5Hex(DigestUtils.md5Hex(file_byte) + orginFileName);
        this.createTime=System.currentTimeMillis()/1000;
        System.out.println(this.md5FileName);
//        SimpleDateFormat dF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        System.out.println(dF.format(new Date()));
    }

    public FileInfo(byte[] file_byte, String orginFileName) {
        this.file_byte = file_byte;
        this.orginFileName = orginFileName;
        System.out.println(this.md5FileName);
    }

    public byte[] getFile_byte() {
        return file_byte;
    }

    public long getFile_size() {
        return file_size;
    }

    public String getOrginFileName() {
        return orginFileName;
    }

    public String getMd5FileName() {
        return md5FileName;
    }

    public long getCreateTime() {
        return createTime;
    }
}
