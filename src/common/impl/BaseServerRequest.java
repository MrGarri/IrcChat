package common.impl;

import common.ServerRequest;
import common.User;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseServerRequest implements ServerRequest {

    User user;
    Map<String, Object> params = new HashMap<>();

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
