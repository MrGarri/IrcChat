package common.messages.impl;

import java.io.Serializable;

public class RegisterRequest extends BaseServerRequest implements Serializable {

    String username;
    String password;

    @Override
    public String getAction() {
        return "register";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
