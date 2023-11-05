import java.sql.*;
import java.util.Properties;

public class Main {

    public static Connection connection = null;

    public static void main(String[] args) {

        // getting props, and setting up props.
        Properties properties = JDBC_Setup.setProps();

        // creating JDBC connection
        connection = JDBC_Setup.databaseConnection(properties, JDBC_Setup.URL);


        // your code

        
        // closing JDBC connection
        JDBC_Setup.databaseClose(connection);



    }
}
