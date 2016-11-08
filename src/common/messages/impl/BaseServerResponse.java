package common.messages.impl;

import common.messages.ServerResponse;

public class BaseServerResponse implements ServerResponse {

    boolean success;

    public BaseServerResponse(boolean success) {
        this.success = success;
    }

    @Override
    public boolean wasSuccessful() {
        return success;
    }

}
