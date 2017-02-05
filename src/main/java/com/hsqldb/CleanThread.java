package com.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author enderculha
 * Works every second, deletes the rows in database specified with a timeout value.
 */
public class CleanThread extends Thread {

    private int           timeout;
    private static String dateFormat  = "yyyy-MM-dd HH:mm:ss";
    static final String   DELETE_DATE = "delete from xdr_iucs where start_time < ?";

    public CleanThread(String name, int timeout) {

        setName(name);
        this.timeout = timeout;
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, -timeout);
            Date oneHourBack = cal.getTime();

            //            Format formatter = new SimpleDateFormat(dateFormat);
            //            String check = formatter.format(oneHourBack);

            Connection connection = null;

            try {
                Class.forName("org.hsqldb.jdbcDriver");
                connection = DriverManager.getConnection(Constants.url, "sa", "");
                PreparedStatement deleteSql = connection.prepareStatement(DELETE_DATE);
                deleteSql.setDate(1, new java.sql.Date(oneHourBack.getTime()));
                deleteSql.execute();
            } catch (SQLException e2) {
                e2.printStackTrace();

            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }

        }

    }

}
