package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class Util {

    public Connection createConnection() {
        try{
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
