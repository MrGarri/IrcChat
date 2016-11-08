package common.messages.impl;

import java.io.Serializable;

public class ErrorServerResponse extends BaseServerResponse implements Serializable {

    private String error;

    public ErrorServerResponse(String error) {
        super(false);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
