package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Util util;

    public UserDaoHibernateImpl(Util util) {
        this.util = new Util();
    }


    @Override
    public void createUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(255) NOT NULL,\n" +
                    "  `last_name` varchar(255) NOT NULL,\n" +
                    "  `age` tinyint(6) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            session.createSQLQuery(query);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "DROP TABLE IF EXISTS `user`";
            session.createSQLQuery(query);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)";
            NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
            nativeQuery.setParameter(1, name);
            nativeQuery.setParameter(2, lastName);
            nativeQuery.setParameter(3, age);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "DELETE FROM user WHERE id = ?";
            NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
            nativeQuery.setParameter(1, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "SELECT id, name, last_name, age FROM user";
            NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
            session.getTransaction().commit();
            session.close();
            return nativeQuery.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String query = "DELETE FROM user";
            session.createNativeQuery(query);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
