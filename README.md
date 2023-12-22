# JDBC Util Package

This package provides a utility class for managing JDBC (Java Database Connectivity) connections to a SQL Server database.

## Overview

The `JDBC` class in this package is designed to simplify the process of establishing a connection to a SQL Server database using properties specified in a configuration file (`db.properties`). It also provides a method to close the database connection.

## Usage

1. **Setup Properties:**

   Before using the `JDBC` class, make sure to set up the `db.properties` file with the required database connection information. The properties file should be located at `src/main/java/com/dmuproject/util/Database/db.properties`.

2. **Instantiate JDBC:**

   Create an instance of the `JDBC` class, which automatically initializes a database connection using the properties from the `db.properties` file.

   ```java
   JDBC jdbc = new JDBC();

## setup   
Get Connection:

1. Retrieve the established database connection using the getConnection method.
    ```java
        Connection connection = jdbc.getConnection();
2. Close Connection:
When done using the database, close the connection using the databaseClose method.
    ```java
        jdbc.databaseClose();

## Configuration
The db.properties file should include the following properties:

`userName` The username for connecting to the database.<br />
`password` The password for connecting to the database.<br />
`ENCRYPT` Encryption setting for the connection.<br />
`databaseName` The name of the database.<br />
`port` The port number for the database connection.<br />

### Drivers
    JDBC -> https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16
