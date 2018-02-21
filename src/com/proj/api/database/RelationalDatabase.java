package com.proj.api.database;

import com.google.gson.reflect.TypeToken;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.proj.api.exception.database.RelationalDatabaseException;
import com.proj.api.utils.JsonUtils;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class RelationalDatabase {
    private static DataSource ds = new ComboPooledDataSource();
    Connection conn=null;
    PreparedStatement pstmt=null;

    public RelationalDatabase() throws RelationalDatabaseException {
        try {
            this.conn=RelationalDatabase.ds.getConnection();
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
    }

    public void commit() throws RelationalDatabaseException {
        try {
            this.conn.commit();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public boolean doSQL(String _sSQL) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement(_sSQL);
            boolean result=this.pstmt.execute();
            this.pstmt.close();
            return result;
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public boolean doSQL(String _sSQL,Object[] aParams) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement(_sSQL);
            for(int i=0;i<aParams.length;i++){
                this.pstmt.setObject(i+1,aParams[i]);
            }
            boolean result=this.pstmt.execute();
            this.pstmt.close();
            return result;
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public ResultSet doQuery(String _sSQL,Object[] aParams) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement(_sSQL);
            for(int i=0;i<aParams.length;i++){
                this.pstmt.setObject(i+1,aParams[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public int getLastInsertId(String _sTable) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement("select last_insert_id() as last_id from " + _sTable);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                return Integer.valueOf(result.getString("last_id"));
            } else {
                return -1;
            }
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e, e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public void rollback() throws RelationalDatabaseException {
        try {
            this.conn.rollback();
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
    }

    public void close() throws RelationalDatabaseException {
        try {
            if(pstmt!=null){
                pstmt.close();
            }
            this.conn.commit();
            this.conn.setAutoCommit(true);
            this.conn.close();
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
    }

    public String ArrayToString(String[] _oArray){
        ArrayList<String> arrayList=new ArrayList<String>();
        for( String pic_token:_oArray){
            arrayList.add(pic_token);
        }
        return JsonUtils.toJson(arrayList);
    }

    public String[] StringToArray(String _sArray){
        return JsonUtils.gson.fromJson(_sArray, new TypeToken<ArrayList<String>>() {}.getType());
    }

}
