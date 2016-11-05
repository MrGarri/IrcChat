package common;

import java.io.Serializable;

public interface ServerRequest extends Serializable {

    String getAction();
    User getUser();
    void setUser(User user);

}
