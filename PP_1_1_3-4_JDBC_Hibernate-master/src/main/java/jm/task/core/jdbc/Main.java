package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Vasya", "Pupkin", (byte) 48);
        service.saveUser("Petr", "Zalupkin", (byte) 38);
        service.saveUser("Pedro", "Muskatti", (byte) 48);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
