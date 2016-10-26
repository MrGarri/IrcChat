package server;

public interface UsersManager {

    boolean userExists(String user);
    boolean login(String user, String password);

}
