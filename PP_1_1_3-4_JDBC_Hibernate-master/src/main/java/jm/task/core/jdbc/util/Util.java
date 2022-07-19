package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/user",
                            "root",
                            "root"
            );
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
       try{
           createConnection().close();
       } catch(SQLException ex) {
           ex.printStackTrace();
       }
    }


}
