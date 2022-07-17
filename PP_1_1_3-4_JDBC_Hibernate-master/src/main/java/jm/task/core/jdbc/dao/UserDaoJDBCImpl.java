package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;

    public UserDaoJDBCImpl(Util util) {
        this.util = util;
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Optional<Connection> connection = Optional.ofNullable(util.createConnection());
        if (connection.isPresent()){
            try{
                Statement statement = connection.get().createStatement();
                statement.executeUpdate("CREATE TABLE `user` (\n" +
                        "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` varchar(100) NOT NULL,\n" +
                        "  `lastName` varchar(100) NOT NULL,\n" +
                        "  `age` smallint(6) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Optional<Connection> connection = Optional.ofNullable(util.createConnection());
        if (connection.isPresent()){
            try{
                Statement statement = connection.get().createStatement();
                statement.executeUpdate("DROP DATABASE IF EXISTS `user`");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Optional<Connection> connection = Optional.ofNullable(util.createConnection());
        if (connection.isPresent()){
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(
                    "INSERT INTO `user` (`name`, `lastName`, `age`) VALUES (?, ?, ?)")){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setLong(3, (long) age);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
