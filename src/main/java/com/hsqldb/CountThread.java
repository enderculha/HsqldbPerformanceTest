package com.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountThread extends Thread {

    public void run() {

        while (!Test.countFlag) {
            System.out.println("count thread working");
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
                ResultSet rs = connection.prepareStatement("select count(*) from xdr_iucs;").executeQuery();
                rs.next();
                int count = rs.getInt(1);
                System.out.println("count(*): " + count);

                if (count > Test.countThreshold) {
                    Test.countFlag = true;
                    System.out.println("Countflag is true");
                }

                rs.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // can through sql exception
        }

    }

}
