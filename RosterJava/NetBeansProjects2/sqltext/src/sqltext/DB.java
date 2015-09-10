/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltext;  
import java.sql.*;  
public class DB {  
String sDBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";     
    //String sConnStr = "jdbc:sqlserver://PC--20110331FNJ:1433;databaseName=productShowSys;integratedSecurity=true;";   
    String sConnStr = "jdbc:odbc:mydb";  
    Connection conn = null;  
    Statement stmt = null;  
    ResultSet rs = null;  
    String url="jdbc:odbc:roster";
    public DB() {  
        try {
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
        Class.forName(sDBDriver);   
        } catch (java.lang.ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
    public ResultSet executeQuery(String sql) {  
        rs = null;  
        try {  
            conn = DriverManager.getConnection(sConnStr);  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql);  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        }  
        return rs;  
    }  
    public void executeUpdate(String sql) {  
        rs = null;  
        try {  
            conn = DriverManager.getConnection(sConnStr);  
            stmt = conn.createStatement();  
            stmt.executeUpdate(sql);  
       } catch (SQLException ex) {  
            ex.printStackTrace();  
       }  
    }  
    public void close() {  
      try {  
          if (stmt != null) {  
                stmt.close();  
                stmt = null;  
            }  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            System.err.println("??��????????????????????: " + e.getMessage());  
        }  
    }  
}  
