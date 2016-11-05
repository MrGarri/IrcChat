package common;

import java.util.Map;

public interface ServerRequest {

    String getAction();
    User getUser();
    void setUser(User user);

}
