import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC_Setup {

    public static final String USERNAME = "sa";
    public static final String PASSWORD = "1234";
    public static final String DATABASE_NAME = "dbChrono5";
    public static final String PORT = "1433";
    public static final String ENCRYPT = "false";
    public static final String URL = "jdbc:sqlserver://localhost:"+ PORT +";databaseName="+DATABASE_NAME;

    public static Properties setProps(){
        // creating an intestines of properties.
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("encrypt", ENCRYPT);

        databaseConnection(properties, URL);
        System.out.printf("%sSetting up props.%s%n", ANSI_YELLOW, ANSI_RESET);
        return properties;
    }

    /***
     * Method used to close a database connection.
     * @param connection object reference to the database.
     */
    public static void databaseClose(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%sClosing connection to JDBC..%s", ANSI_YELLOW, ANSI_RESET);
    }

    /***
     * method for creating a connection to a database
     * @param properties for the database. (set in JDBC_Setup.class)
     * @param URL connection string (set in JDBC_Setup.class)
     * @return connection an obj with connection to the database.
     */
    public static Connection databaseConnection(Properties properties, String URL){

        // initializes connection.
        Connection connection = null;

        // JDBC tries to connect to SQL database add URL.
        try {
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%sCreating connection.%s%n", ANSI_YELLOW, ANSI_RESET);
        return connection;

    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

}
