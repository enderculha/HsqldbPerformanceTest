package com.hsqldb;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConsoleThread extends Thread {

    public void run() {
        Console cnsl = null;
        String command = null;

        while (true) {
            try {
                // creates a console object
                cnsl = System.console();

                // if console is not null
                if (cnsl != null) {

                    // read line from the user input
                    command = cnsl.readLine("Enter Command: ");

                    ExecuteCommand(command);

                }
            } catch (Exception ex) {

                // if any error occurs
                ex.printStackTrace();
            }
        }
    }

    private static void ExecuteCommand(String command) {
        // TODO Auto-generated method stub

        String[] SplittedCommand = command.split(" ");
        if (SplittedCommand[0].equals("e")) {
            System.out.println("Explain Plan for Select number:" + SplittedCommand[1]);
            Explainfor(SplittedCommand[1]);

        } else if (SplittedCommand[0].equals("sl")) {
            System.out.println("Select Like  number:" + SplittedCommand[1]);
            SelectLike(SplittedCommand[1]);

        } else if (SplittedCommand[0].equals("s")) {
            System.out.println("Select number:" + SplittedCommand[1]);
            Select(SplittedCommand[1]);

        } else if (SplittedCommand[0].equals("el")) {
            System.out.println("Explain Plan for Select Like number:" + SplittedCommand[1]);
            ExplainPlanForLike(SplittedCommand[1]);
        } else {
            System.out.println("Wrong command format");
        }
    }

    private static void ExplainPlanForLike(String bnumber) {
        // TODO Auto-generated method stub
        Connection connection = null;
        ResultSet rs = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -61);
            Date oneHourBack = cal.getTime();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String SqlExplainFor = "EXPLAIN PLAN FOR SELECT * FROM xdr_iucs WHERE b_number LIKE  '%"
                    + bnumber + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            long start = System.currentTimeMillis();
            rs = connection.prepareStatement(SqlExplainFor).executeQuery();

            if (rs.next()) {
                System.out.println("Numara database'de bulundu.");
            } else {
                System.out.println("Numara databasede bulunamadý.");
            }

            /*   ResultSet rs2 = connection.prepareStatement(SqlExplainFor).executeQuery();

               while (rs2.next()) {
                   System.out.println("Explain plan for:" + rs2.getString(1));
               }
               rs2.close();*/

            long durSelect = System.currentTimeMillis() - start;
            System.out.println(" Explain Plan For Select Like Duration:                                               "
                    + durSelect + "ms");

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void Select(String bnumber) {
        // TODO Auto-generated method stub
        Connection connection = null;
        ResultSet rs = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -61);
            Date oneHourBack = cal.getTime();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sqlSelect = "SELECT * FROM xdr_iucs WHERE b_number ='90505" + bnumber
                    + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            long start = System.currentTimeMillis();
            rs = connection.prepareStatement(sqlSelect).executeQuery();

            if (rs.isBeforeFirst()) {
                System.out.println("Numara database'de bulundu.");
            } else {
                System.out.println("Numara databasede bulunamadý.");
            }

            while (rs.next()) {
                System.out.println("start_time:" + rs.getString("start_time") + "  " + "b_number:"
                        + rs.getString("b_number"));
            }

            /*   ResultSet rs2 = connection.prepareStatement(SqlExplainFor).executeQuery();

               while (rs2.next()) {
                   System.out.println("Explain plan for:" + rs2.getString(1));
               }
               rs2.close();*/

            long durSelect = System.currentTimeMillis() - start;
            System.out.println(" Select Duration:                                               "
                    + durSelect + "ms");

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void SelectLike(String bnumber) {
        // TODO Auto-generated method stub

        Connection connection = null;
        ResultSet rs = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -61);
            Date oneHourBack = cal.getTime();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sqlSelectL = "SELECT * FROM xdr_iucs WHERE b_number LIKE  '%" + bnumber
                    + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            long start = System.currentTimeMillis();
            rs = connection.prepareStatement(sqlSelectL).executeQuery();

            if (rs.isBeforeFirst()) {
                System.out.println("Numara database'de bulundu.");
            } else {
                System.out.println("Numara databasede bulunamadý.");
            }

            while (rs.next()) {
                System.out.println("start_time:" + rs.getString("start_time") + "  " + "b_number:"
                        + rs.getString("b_number"));
            }

            /*   ResultSet rs2 = connection.prepareStatement(SqlExplainFor).executeQuery();

               while (rs2.next()) {
                   System.out.println("Explain plan for:" + rs2.getString(1));
               }
               rs2.close();*/

            long durSelect = System.currentTimeMillis() - start;
            System.out.println(" Select Like Duration:                                               "
                    + durSelect + "ms");

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void Explainfor(String bnumber) {
        // TODO Auto-generated method stub

        Connection connection = null;
        ResultSet rs = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -61);
            Date oneHourBack = cal.getTime();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String SqlExplainFor = "EXPLAIN PLAN FOR SELECT * FROM xdr_iucs WHERE b_number= '90505"
                    + bnumber + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            long start = System.currentTimeMillis();
            rs = connection.prepareStatement(SqlExplainFor).executeQuery();

            /*   ResultSet rs2 = connection.prepareStatement(SqlExplainFor).executeQuery();

               while (rs2.next()) {
                   System.out.println("Explain plan for:" + rs2.getString(1));
               }
               rs2.close();*/

            long durSelect = System.currentTimeMillis() - start;
            System.out.println(" Explain Plan For Select Duration:                                               "
                    + durSelect + "ms");

            if (rs.next()) {
                System.out.println("Numara database'de bulundu.");
            } else {
                System.out.println("Numara databasede bulunamadý.");
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
