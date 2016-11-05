package common.impl;

import common.ServerRequest;
import common.User;

public class CreateRoomRequest implements ServerRequest {

    String name;
    User user;

    @Override
    public String getAction() {
        return "createRoom";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
