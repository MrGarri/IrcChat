package common.impl;

import common.ServerRequest;
import common.User;

public abstract class BaseServerRequest implements ServerRequest {

    User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

}
