package common.impl;

import common.ServerResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseServerResponse implements ServerResponse{

    Map<String, Object> params = new HashMap<>();
    boolean success;

    public BaseServerResponse(boolean success) {
        this.success = success;
    }

    @Override
    public boolean wasSuccessful() {
        return success;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
