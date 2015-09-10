/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltext;

import java.sql.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author shahua
 */
public class Sqltext {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //get connection
        Connection conn = null;
        try {
//        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

       // DB d=new DB();
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("ssssss  ");
        try {
            //conn = DriverManager.getConnection("jdbc:odbc:dbpoolname",  "sa", "123456");  
            conn = DriverManager.getConnection("jdbc:sqlserver://testsql:1433;DatabaseName5=roster", "shahua", "4321");
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e);
        }

       
  
      
        
        //select
        /*
        ArrayList<Object[]> list = new ArrayList();
        String q = "select name,password from users";
        int j = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                j++;
                Object note[] = new Object[2]; // number of colum which you want to get
                for (int i = 0; i < note.length; i++) {
                    note[i] = rs.getObject(i + 1);
                    System.out.println(note[i] + "   " + i);
                    //System.out.println(i);
                }
                list.add(note);

            }

            stmt.close();
            conn.close();
            //System.out.println(list.get(0)[0] + "   444");
        } catch (Exception e) {
            System.out.println(e);

        }
        */
        
        //insert
        /*
        //String q="insert into users (name, password) values ('Li Gang', '8765')";
       String q="use roster insert into specialrequire (name) values ('Amanda Faraonio')";
        try{
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = null;
            pstmt=conn.prepareCall(q);
            pstmt.executeUpdate();
            
            stmt.close();
            conn.close();
            System.out.println("inserted");
        }catch (Exception e){
            System.out.println(e);
        }
        */
        
        //delete
        /*
        String q="delete from specialrequire where name='ggg'";
        try{
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = null;
            pstmt=conn.prepareCall(q);
            pstmt.executeUpdate();
            
            stmt.close();
            conn.close();
            System.out.println("deleted ");
        }catch(Exception e){
            System.out.println(e);
        }
        */
        
        //create
        
         //String q="use roster create table people(client_id nvarchar(10) NOT NULL, client_name nvarchar(50) not null)";
        /*
         String q="use roster create table nonbaseday_noar(name nchar(20) NOT NULL, cc1 int, cc2 int,cc3 int,cc4 int,cc5 int,cc6 int,cc7 int,cc8 int,cc9 int,fc1 int, fc2 int, fc3 int, ccb int, fc2n int, fc2cc int, fc2bu int)";
        try{
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = null;
            pstmt=conn.prepareCall(q);
            pstmt.executeUpdate();
            
            stmt.close();
            conn.close();
            System.out.println("deleted ");
        }catch(Exception e){
            System.out.println(e);
        }
        
        */
        
    }

}
