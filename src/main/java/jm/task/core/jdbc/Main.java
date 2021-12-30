package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Aleksei", "Bissing", (byte) 41);
        userService.saveUser("Andrei", "Yakovlev", (byte) 37);
        userService.saveUser("Aleksandr", "Zimin", (byte) 36);
        userService.saveUser("Michail", "Chebotaryov", (byte) 38);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
