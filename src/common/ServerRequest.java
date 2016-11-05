package common;

public interface ServerRequest {

    String getAction();
    User getUser();
    void setUser(User user);

}
