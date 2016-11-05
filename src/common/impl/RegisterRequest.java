package common.impl;

import common.ServerRequest;
import common.User;

public class RegisterRequest extends BaseServerRequest {

    public RegisterRequest(String username, String password) {
        this.params.put(username, password);
    }

    @Override
    public String getAction() {
        return "register";
    }

}
