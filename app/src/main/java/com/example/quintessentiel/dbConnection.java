package com.example.quintessentiel;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    String classString = "com.mysql.jdbc.Driver";

    String  url="jdbc:mysql://127.0.0.1/quintessentieldb";
    String user="root";
    String password="";

    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String connUrl = null;

        try {
            Class.forName(classString);

            conn = DriverManager.getConnection(url, user, password);
            conn = DriverManager.getConnection(connUrl);
        } catch (SQLException se){
            Log.e("ERROR", se.getMessage());
        }catch (ClassNotFoundException e){
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return conn;

    }
}
