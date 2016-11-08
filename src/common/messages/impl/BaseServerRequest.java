package common.messages.impl;

import common.messages.ServerRequest;
import common.dto.User;

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
