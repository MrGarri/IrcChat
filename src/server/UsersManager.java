package server;

import common.dto.User;

public interface UsersManager {

    boolean userExists(String user);
    User addUser(String user, String password);
    boolean login(String user, String password);

}
