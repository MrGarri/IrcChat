package common.impl;

import common.ServerRequest;
import common.User;

import java.util.HashMap;
import java.util.Map;

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
