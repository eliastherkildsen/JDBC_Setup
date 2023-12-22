import java.sql.*;
import java.util.Properties;

public class Main {

    public static JDBC db = new JDBC();
    public static Connection connection = db.getConnection();

    public static void testDB(){
        PreparedStatement getProject;
        try {
            getProject = connection.prepareCall("SELECT * FROM tblProject where fldProjectID=" + 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            ResultSet projectDetails = getProject.executeQuery();
            while (projectDetails.next()) {

                String projectId = projectDetails.getString("fldProjectID");
                String projectName = projectDetails.getString("fldProjectName");
                String projectStart = projectDetails.getString("fldProjectStartDate");
                String projectEnd = projectDetails.getString("fldProjectEndDate");

                System.out.printf("%s%s%n", AnsiColorCode.ANSI_GREEN,"Project ID: " + projectId);
                System.out.printf("%s%n","Project name: " + projectName);
                System.out.printf("%s%n","Start date: " + projectStart);
                System.out.printf("%s%s%n","End date: " + projectEnd,AnsiColorCode.ANSI_RESET);
            }
        } catch (SQLException ignore) {}
    }


    public static void main(String[] args) {

        public static JDBC db = new JDBC();
        public static Connection connection = db.getConnection();
        testDB(); 
        }

    
}

