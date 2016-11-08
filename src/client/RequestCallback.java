package client;

import common.messages.ServerResponse;
import common.messages.impl.ErrorServerResponse;

public interface RequestCallback {

    void success(ServerResponse response);
    void failure(ErrorServerResponse response);

}
