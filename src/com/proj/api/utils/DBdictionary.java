package com.proj.api.utils;

import java.sql.*;

public class DBdictionary {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/wjxt";
    static final String USER = "root";
    static final String PASS = "root";
    public DBdictionary(String file_token, String user_id, String privage_user, String file_name, long create_time, long file_size ) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到mysql数据库！");
            String sql = "INSERT INTO permanentfile(file_token,user_id,privage_user,file_name,create_time,file_size) VALUES (?,?,?,?,?,?)";    //要执行的SQL
                try {
                    PreparedStatement preStmt=conn.prepareStatement(sql);
                    preStmt.setString(1, file_token);
                    preStmt.setString(2, user_id);
                    preStmt.setString(3, privage_user);
                    preStmt.setString(4, file_name);
                    preStmt.setLong(5, create_time);
                    preStmt.setLong(6, file_size);
                    preStmt.executeUpdate();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
            stmt.close();
            conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
