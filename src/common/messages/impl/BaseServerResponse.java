package common.messages.impl;

import common.messages.ServerResponse;

import java.io.Serializable;

public class BaseServerResponse<T> implements ServerResponse<T>, Serializable {

    boolean success;
    T data;

    public BaseServerResponse(boolean success) {
        this.success = success;
    }

    @Override
    public boolean wasSuccessful() {
        return success;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

}
