package common.dto;

import java.io.Serializable;

public class Room implements Serializable {

    public User user;
    public String name;

    public Room(String name, User user) {
        this.user = user;
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}