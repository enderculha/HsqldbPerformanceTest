package com.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectThread extends Thread {
	
    public SelectThread(String name) {

        setName(name);
    }

	
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                Class.forName("org.hsqldb.jdbcDriver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test",
                                                                    "sa",
                                                                    "");
                String b_number = "905078127926";
                String sqlSelect = "SELECT * FROM xdr_iucs WHERE b_number = " + b_number + ";";
                
                long start = System.currentTimeMillis();
                ResultSet rs = connection.prepareStatement(sqlSelect).executeQuery();
                long dur = System.currentTimeMillis() - start;
                
               /* while(rs.next()){
                	//System.out.println(getName() + " Selected Query: " + rs.getString("b_number"));
 
                }*/
                
                
                  
                System.out.println(getName() + " Select Query Duration:" + dur);
                rs.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // can through sql exception
        }

    }

}
