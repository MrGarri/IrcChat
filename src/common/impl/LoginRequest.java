package common.impl;

import common.ServerRequest;
import common.User;

public class LoginRequest extends BaseServerRequest {

    public LoginRequest(String username, String password) {
        this.params.put(username, password);
    }

    @Override
    public String getAction() {
        return "login";
    }



}
