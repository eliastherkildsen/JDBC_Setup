package com.dmuproject.util.Database;

// @auth Elias B. Therkildsen 22.12.2023

import com.dmuproject.util.AnsiColorCode;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {
    private String URL;
    private Connection connection;
    private String DATBASE_PROPS_PATH = "src/main/java/com/dmuproject/util/Database/db.properties";


    public JDBC(){
        connection = createConnection(setProps());
    }

    private Properties setProps(){

        // ConsoleLog
        System.out.printf("%s[JDBC] Trying to setup props.%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        Properties properties = new Properties();
        File file = new File(DATBASE_PROPS_PATH);
        InputStream input;


        try {
            input = new FileInputStream(file);
            try {

                properties.load(input);

                properties.setProperty("user", properties.getProperty("userName"));
                properties.setProperty("password", properties.getProperty("password"));
                properties.setProperty("encrypt", properties.getProperty("ENCRYPT"));

                String DATABASE_NAME = properties.getProperty("databaseName");
                String PORT = properties.getProperty("port");

                URL = "jdbc:sqlserver://localhost:"+ PORT +";databaseName="+DATABASE_NAME;

                System.out.printf("%s[JDBC] successful in setting up props! %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

            } catch (IOException e) {
                System.out.printf("%s[JDBC] Error! ' %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            System.out.printf("%s[JDBC] failed to finde the file 'db.properties' %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            throw new RuntimeException(e);
        }

        return properties;
    }

    /***
     * Method used to close a database connection.
     * @param connection object reference to the database.
     */
    public void databaseClose(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%sClosing connection to JDBC..%s", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
    }

    /***
     * method for creating a connection to a database
     * @param properties for the database. (set in JDBC_Setup.class)
     * @return connection an obj with connection to the database.
     */
    private Connection createConnection(Properties properties){

        // initializes connection.
        Connection connection = null;

        // JDBC tries to connect to SQL database add URL.
        try {
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%sCreating connection.%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        return connection;

    }

    public Connection getConnection() {
        return connection;
    }
}
