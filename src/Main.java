import java.sql.*;
import java.util.Properties;

public class Main {

    // database name.
    private static final String databaseName = "dbTest";

    public static void main(String[] args) throws SQLException {

        // creating connection String
        String url = "jdbc:sqlserver://localhost:1433;databaseName="+databaseName;

        // creating the property's for JDBC connection.
        Properties properties = new Properties();

        // Creating user parameters
        properties.setProperty("user", "sa");
        properties.setProperty("password", "1234");

        // set the SSL property
        properties.setProperty("encrypt", "false");

        // create connection.
        Connection connection = DriverManager.getConnection(url, properties);


        System.out.println("Hello JDBC");

        // Updating prepaired statments.
        //PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblUser (fldID, fldName) VALUES (6, 'elias')");
        PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM tblUser");

        // setting up result set
        //ResultSet resultSet = preparedStatement.executeQuery(); // Read type.
        //preparedStatement.executeUpdate();


        try {
            // Assuming you already have a PreparedStatement object named preparedStatement
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming "tblUser" has columns like "column1", "column2", etc.
                int column1Value = resultSet.getInt("fldID");
                String column2Value = resultSet.getString("fldName");
                // Retrieve other columns as needed

                System.out.println("column1: " + column1Value + ", column2: " + column2Value);
                // Print other columns as needed
            }

            resultSet.close();
        } catch (SQLException e) {


        }




    }

}