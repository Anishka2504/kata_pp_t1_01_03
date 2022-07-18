package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private final Util util;

    public UserDaoJDBCImpl(Util util) {
        this.util = util;
    }

    public void createUsersTable() {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(100) NOT NULL,\n" +
                    "  `lastName` varchar(100) NOT NULL,\n" +
                    "  `age` smallint(6) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            try (Statement statement = connection.get().createStatement();) {
                statement.execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "DROP TABLE IF EXISTS `user`";
            try (Statement statement = connection.get().createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "INSERT INTO `user` (`name`, `lastName`, `age`) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("User is added");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void removeUserById(long id) {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "DELETE FROM user WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("User is removed");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "SELECT id, name, last_name, age FROM user";
            try (Statement statement = connection.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                List<User> result = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setAge(resultSet.getByte("age"));
                    result.add(user);
                }
                return result;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public void cleanUsersTable() {
        Optional<Connection> connection = util.createConnection();
        if (connection.isPresent()) {
            String query = "DELETE FROM user";
            try (Statement statement = connection.get().createStatement()) {
                statement.execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
