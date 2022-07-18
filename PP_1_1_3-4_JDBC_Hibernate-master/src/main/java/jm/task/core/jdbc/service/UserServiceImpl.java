package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoJDBCImpl(new Util());
    private final UserDao hibernateUser = new UserDaoHibernateImpl(new Util());

    public void createUsersTable() {
//        userDao.createUsersTable();
        hibernateUser.createUsersTable();
    }

    public void dropUsersTable() {
//        userDao.dropUsersTable();
        hibernateUser.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDao.saveUser(name, lastName, age);
        hibernateUser.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
//        userDao.removeUserById(id);
        hibernateUser.removeUserById(id);
    }

    public List<User> getAllUsers() {
//        return userDao.getAllUsers();
       return hibernateUser.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDao.cleanUsersTable();
        hibernateUser.cleanUsersTable();
    }
}
