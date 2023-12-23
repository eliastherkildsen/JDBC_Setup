/**
 * The JDBC class provides functionality to connect to a SQL database using JDBC.
 * It reads database connection properties from a configuration file and creates a connection.
 *
 * @author Elias B. Therkildsen
 * @version 1.0
 * @since 22.12.2023
 */
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    // ANSI color codes for console output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    // Path to the database properties file
    private String DATBASE_PROPS_PATH = "src/util/Database/db.properties";

    // Database connection URL
    private String URL;

    // Connection object to interact with the database
    private Connection connection;

    /**
     * Constructs a JDBC instance and initializes a database connection.
     */
    public JDBC() {
        connection = createConnection(setProps());
    }

    /**
     * Reads database properties from a configuration file and sets up a Properties object.
     *
     * @return Properties object containing database connection properties.
     * @throws RuntimeException if an error occurs during file reading or property setting.
     */
    private Properties setProps() {
        System.out.printf("%s[JDBC] Trying to setup props.%s%n", ANSI_YELLOW, ANSI_RESET);

        Properties properties = new Properties();
        File file = new File(DATBASE_PROPS_PATH);
        InputStream input;

        try {
            input = new FileInputStream(file);
            try {
                properties.load(input);
                properties.setProperty("user", properties.getProperty("user"));
                properties.setProperty("password", properties.getProperty("password"));
                properties.setProperty("encrypt", properties.getProperty("encrypt"));

                String DATABASE_NAME = properties.getProperty("databaseName");
                String PORT = properties.getProperty("port");
                String IP = properties.getProperty("ip");

                URL = "jdbc:sqlserver://" + IP + ":" + PORT + ";databaseName=" + DATABASE_NAME;

                System.out.printf("%s[JDBC] Successful in setting up props! %s%n", ANSI_YELLOW, ANSI_RESET);

            } catch (IOException e) {
                System.out.printf("%s[JDBC] Error! ' %s%n", ANSI_RED, ANSI_RESET);
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("%s[JDBC] Failed to find the file 'db.properties' %s%n", ANSI_RED, ANSI_RESET);
            throw new RuntimeException(e);
        }

        return properties;
    }

    /**
     * Closes the database connection.
     *
     * @throws RuntimeException if an error occurs while closing the connection.
     */
    public void databaseClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s[JDBC] Closing connection to JDBC..%s", ANSI_YELLOW, ANSI_RESET);
    }

    /**
     * Creates a database connection using the specified properties.
     *
     * @param properties Properties object containing database connection properties.
     * @return Connection object representing the connection to the database.
     * @throws RuntimeException if an error occurs while creating the connection.
     */
    private Connection createConnection(Properties properties) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s[JDBC] Creating connection.%s%n", ANSI_YELLOW, ANSI_RESET);
        return connection;
    }

    /**
     * Gets the current database connection.
     *
     * @return Connection object representing the current database connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
