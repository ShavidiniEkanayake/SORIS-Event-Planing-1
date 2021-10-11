package com.soris.SORIS_planing.sp.Service.models;

import com.soris.SORIS_planing.dbUtil;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class updateServiceModel {
    private boolean isSuccess = false;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs;

    public service getUpdateDetails(int sID){
        try{
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();

            String sql = "SELECT name, category, price, discount, description, status, images FROM service WHERE sid ='"+sID+"'";
            ResultSet rs=stmt.executeQuery(sql);

            rs.next();
            String name = rs.getString(1);
            String category = rs.getString(2);
            double price = rs.getDouble(3);
            double discount = rs.getDouble(4);
            String description = rs.getString(5);
            String status = rs.getString(6);
            Blob image = rs.getBlob("images");

            byte imageByteArray[] = image.getBytes(1, (int) image.length());
            service s = new service(sID , name, category, price, discount, description, status,imageByteArray);
            return s;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //update service
    public boolean updateService(int sID, String name, double price, double discount, String category, String description, Part img){

        try{
            con = com.soris.SORIS_planing.dbUtil.initializeDatabase();
            stmt = con.createStatement();
            InputStream imgBlob = img.getInputStream();
            PreparedStatement stmt = this.con.prepareStatement("UPDATE service set name = ?, price= ?, discount= ?,category= ?, description= ?, images=?, status='pending' WHERE sid = ?");
            stmt.setString(1,name);
            stmt.setDouble(2,price);
            stmt.setDouble(3,discount);
            stmt.setString(4,category);
            stmt.setString(5,description);
            stmt.setBlob(6,imgBlob);
            stmt.setInt(7,sID);
//            String sql = "UPDATE service set name = '"+name+"', price= '"+price+"', discount= '"+discount+"',category='"+category+"', description= '"+description+"', status='pending' WHERE sid = '"+sID+"'";//, category= '"+category+"'

            int rs = stmt.executeUpdate();

            if(rs > 0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }

        }catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e);
        }
        return isSuccess;
    }
}
