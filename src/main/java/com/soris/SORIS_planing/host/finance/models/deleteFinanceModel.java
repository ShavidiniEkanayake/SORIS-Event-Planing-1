package com.soris.SORIS_planing.host.finance.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.soris.SORIS_planing.dbUtil;

public class deleteFinanceModel {
    private static Connection con;

    public deleteFinanceModel() throws SQLException, ClassNotFoundException{
        this.con = dbUtil.initializeDatabase();
    }
    public static boolean deleteFinance(String fid)
    {
        try{
            Statement stmt = con.createStatement();
            String sql = "";
            int count = stmt.executeUpdate(sql);
            if(count > 0){
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}

