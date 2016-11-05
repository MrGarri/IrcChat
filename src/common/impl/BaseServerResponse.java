package common.impl;

import common.ServerResponse;

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
