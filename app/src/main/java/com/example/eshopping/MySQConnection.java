package com.example.eshopping;
import android.content.Context;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQConnection {
    Connection conn = null;
    String result = " ";
    String DB_NAME = "eshopping";
    Context context;

    public MySQConnection(Context context) {
        this.context = context;
    }

    public ArrayList<CategoryModel> getAllCategoryData(){
        ArrayList<CategoryModel> getCategoryData = new ArrayList<CategoryModel>();

        try {
            String usr = "root";
            String password = "ravirishuu@4210002";
//            String url = "jdbc:mysql://127.0.0.1:3306/eshopping";
//            String url = "jdbc:mysql://localhost:3306/DB_NAME";
            String url = "jdbc:mysql://192.168.1.5:3306/eshopping";
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,usr,password);
            if (conn != null) {
                System.out.println("Connected to the database eshopping");
            }
            String sql;
            sql = "SELECT * FROM " + "category";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null && rs.first()) {
                String categoryID, categoryName, categoryImage;
                do {
                    categoryID = rs.getString(0);
                    categoryName = rs.getString(1);
                    categoryImage = rs.getString(2);
                    System.out.println("Hello");
//                    getCategoryData.add(new CategoryModel(categoryID, categoryName, categoryImage));
                } while (rs.next());
            }
            rs.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MySQConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
//        getCategoryData.add(new CategoryModel("12","Hello","https://5.imimg.com/data5/LJ/RP/MY-4878239/stationery-500x500.png"));
        return getCategoryData;
    }
}