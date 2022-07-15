package dom.com.gm.jdbc;

import java.sql.*;


/**
 * How to do a Java Data Base Conectivity
 * First: Connection String
 * Second: Initialize Connection type using static method DriverManager.getConnection
 * Third: Create statement object using .createStatement from the Connection Object.
 * Fourth: obtain sql statement string
 * Fifth: Execute query using statement object and store it on a ResultSet object.
 * Sixth: Iterate the ResultSet result using preferably the column name.
 */
public class TestMySQLJDBC 
{
    public static void main( String[] args )
    {   
        /*
         * Connection URL: The connection URL for the mysql database is jdbc:mysql://localhost:3306/sonoo where jdbc is the API, mysql is the database, 
         * localhost is the server name on which mysql is running, we may also use IP address, 3306 is the port number and sonoo is the database name. 
         * We may use any database, in such case, we need to replace the sonoo with our database name.
         */
        //ConnectionString for MySQL in this case:
        var url = "jdbc:mysql://127.0.0.1:3306/test"+
        "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true"; //API:mysql://hostIP:port/dbName
        
        var user = "root"; var pw = "admin";
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver"); //Can be needed for web applications.
            Connection dbConnection = DriverManager.getConnection(url, user, pw);
            Statement instruction = dbConnection.createStatement(); //java.sql implemented interface.

            var sqlStatement = "SELECT person_id, person_name, person_lastname, person_email, person_phone FROM test.person";
            ResultSet result = instruction.executeQuery(sqlStatement);

            while (result.next()) {
                System.out.println("---------------------");
                System.out.println("[PERSON_ID] "+result.getInt("person_id"));
                System.out.println("[PERSON_NAME] "+result.getString("person_name"));
                System.out.println("[PERSON_LASTNAME] "+result.getString("person_lastname"));
                System.out.println("[PERSON_EMAIL] "+result.getString("person_email"));
                System.out.println("[PERSON_PHONE] "+result.getLong("person_phone"));
            }
            result.close();
            instruction.close();
            dbConnection.close();
        } /*catch (ClassNotFoundException e) { 
            // This exception is for Class.forName
            e.printStackTrace(System.out);
            System.out.println(e.getMessage());
        }*/ catch (SQLException e) {
            // This one is for the Driver Manager Connection
            e.printStackTrace(System.out);
            System.out.println(e.getMessage());
        }
    }
}
