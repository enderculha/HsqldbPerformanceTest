package com.hsqldb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class Test {

    public static int     thread_count   = 10;

    public static boolean countFlag      = false;
    public static int     countThreshold = 1000000;

    public static int     timeout        = 61;

    public static void main(String[] args) {

        InsertThread.insert_count = Integer.parseInt(args[0]);
        InsertThread.insert_size = Integer.parseInt(args[1]);
        thread_count = Integer.parseInt(args[2]);
        timeout = Integer.parseInt(args[3]);
        Connection connection = null;

        Constants.hsqlServer = new Server();
        Constants.hsqlServer.setLogWriter(null);
        Constants.hsqlServer.setSilent(true);
        Constants.hsqlServer.setDatabaseName(0, "test");
        Constants.hsqlServer.setDatabasePath(0, "mem:/ender_hsqldb/testhsqldb");

        HsqlProperties p = new HsqlProperties();
        p.setProperty("hsqldb.tx", "MVCC");

        try {
            Constants.hsqlServer.setProperties(p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AclFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Constants.hsqlServer.start();

        // making a connection
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(Constants.url, "sa", ""); // can through sql exception

            connection.createStatement().executeUpdate("SET DATABASE TRANSACTION CONTROL MVCC");
            connection.commit();

            org.hsqldb.util.DatabaseManagerSwing.main(new String[] {"--url", Constants.url,
                    "--noexit"});


            connection.prepareStatement("drop table xdr_iucs if exists;").execute();


            connection.prepareStatement(Constants.create).execute();

            connection.prepareStatement(Constants.index3).execute();
            for (int i = 0; i < thread_count; i++) {
                new InsertThread("inserter" + i).start();

            }

            new CountThread().start();
            new CleanThread("clean", timeout).start();
            new ConsoleThread().start();


        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

    }
}
