package common.impl;

import common.ServerRequest;

public class RegisterRequest implements ServerRequest {

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
