package server.impl;

import common.dto.User;
import server.Server;
import server.UsersManager;

import java.util.HashMap;
import java.util.Map;

public class UsersManagerImpl implements UsersManager{

    Map<String, User> users = new HashMap<>();
    Server server;

    public UsersManagerImpl(Server server) {
        this.server = server;
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    @Override
    public User addUser(String username, String password) {

        User user = new User(username, password);
        users.put(username, user);

        return user;

    }

    @Override
    public boolean login(String username, String password) {
        User user = users.get(username);

        return user != null && user.getPassword().equals(password);

    }
}
