package client;

import common.ServerResponse;
import common.impl.ErrorServerResponse;

public interface RequestCallback {

    void success(ServerResponse response);
    void failure(ErrorServerResponse response);

}
