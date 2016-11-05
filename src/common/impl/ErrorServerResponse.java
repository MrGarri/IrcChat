package common.impl;

public class ErrorServerResponse extends BaseServerResponse{

    private String error;

    public ErrorServerResponse(String error) {
        super(false);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
