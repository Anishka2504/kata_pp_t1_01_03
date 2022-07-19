package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Vasya", "Pupkin", (byte) 48);
        service.saveUser("Ivan", "Ivanov", (byte) 38);
        service.saveUser("Pedro", "Muskatti", (byte) 48);
        service.saveUser("Fernando", "Kartoshkin", (byte) 70);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
        Util.closeConnection();
    }
}
