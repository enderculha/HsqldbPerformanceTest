package com.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author enderculha
 * 
 * This thread inserts to table continously while working.
 * Data size inserted is insert_count x insert_size 
 * In every data insert, b_number is changed by incrementing the value by 1.
 * When data size in DB reaches a threshold value, SelectandDelete() method starts working. This method
 * generates a random b_number and make the query of 
 * "SELECT * FROM xdr_iucs WHERE b_number LIKE  '%" + bnumber + "' AND 'start_time' > '" +ft.format(oneHourBack.getTime()) + "';";
 * If the query returns a row, this row is deleted from the Database.
 * SelectandDelete() method works the same as insert_count x insert_size 
 */
public class InsertThread extends Thread {

    public static int insert_count = 1; // Default insert count
    public static int insert_size  = 100;//Default insert size

    public InsertThread(String name) {

        setName(name);
    }

    public void run() {

        while (true) {
            bulkInsert();
        }
    }

    public void bulkInsert() {

        StringBuffer[] sql = new StringBuffer[insert_count];

        for (int i = 0; i < sql.length; i++) {
            sql[i] = new StringBuffer();
        }

        for (int j = 0; j < insert_count; j++) {

            sql[j].append(Constants.sql_start);

            for (int i = 0; i < insert_size; i++) {

                // If database size is larger then threshold start select and
                // delete
                 if (Test.countFlag) {
                      selectandDelete();
                  }
                Constants.updateRow();
                sql[j].append(Constants.row);
                if (i != insert_size - 1)
                    sql[j].append(" , ");
            }
            sql[j].append(";");
        }
        long start = System.currentTimeMillis();

        Connection connection = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(Constants.url, "sa", "");
        } catch (SQLException e2) {
            e2.printStackTrace();

        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        for (int j = 0; j < insert_count; j++) {

            try {
                connection.prepareStatement(sql[j].toString()).execute();

            } catch (SQLException e2) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                e2.printStackTrace();
            }

        }

        long dur = System.currentTimeMillis() - start;
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("inserted :" + insert_count * insert_size);
        System.out.println("Thread: " + getName() + "   Duration : " + (dur) + "  for 1 " + dur
                / (insert_count * insert_size) + " msec\n\n");
    }

    private void selectandDelete() {
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
            String sn = Integer.toString(InsertThread.randMSISDN(0, 9999999));
            for (int i = 7 - sn.length(); i > 0; i--) {
                sn = 0 + sn;
            }

            String bnumber = sn;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -61);
            Date oneHourBack = cal.getTime();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sqlSelect = "SELECT * FROM xdr_iucs WHERE b_number LIKE  '%" + bnumber
                    + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            String SqlExplainFor = "EXPLAIN PLAN FOR SELECT * FROM xdr_iucs WHERE b_number LIKE  '%"
                    + bnumber + "' AND 'start_time' > '" + ft.format(oneHourBack.getTime()) + "';";

            long start = System.currentTimeMillis();
            rs = connection.prepareStatement(sqlSelect).executeQuery();

            /*   ResultSet rs2 = connection.prepareStatement(SqlExplainFor).executeQuery();

               while (rs2.next()) {
                   System.out.println("Explain plan for:" + rs2.getString(1));
               }
               rs2.close();*/

            long durSelect = System.currentTimeMillis() - start;
            System.out.println("Thread: " + getName()
                    + " Select Duration:                                               "
                    + durSelect + "ms");

            long startDelete = System.currentTimeMillis();

            while (rs.next()) {
                // ResultSet is not empty delete the record
                String b = rs.getString("b_number");

                String SqlDelete = "DELETE FROM xdr_iucs WHERE b_number = " + b + ";";

                connection.prepareStatement(SqlDelete).execute();
                System.out.println("deleting: " + b);
            }

            long durDelete = System.currentTimeMillis() - startDelete;
            System.out.println("Thread: " + getName()
                    + " Delete Duration:                         " + durDelete + "ms");

            long dur = System.currentTimeMillis() - start;
            System.out.println("Thread: " + getName() + " Select+Delete Duration:" + dur + "ms");
            rs.close();
            connection.close();

        } catch (SQLException e) {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            e.printStackTrace();
        } // can through sql exception
    }

    public static int randMSISDN(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
