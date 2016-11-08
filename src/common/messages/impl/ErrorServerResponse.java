package common.messages.impl;

import java.io.Serializable;

public class ErrorServerResponse extends BaseServerResponse<String> implements Serializable {

    public ErrorServerResponse(String error) {
        super(false);
        setData(error);
    }

}
