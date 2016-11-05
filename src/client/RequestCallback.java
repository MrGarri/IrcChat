package client;

import common.ServerResponse;

public interface RequestCallback {

    void success(ServerResponse response);
    void failure(ServerResponse response);

}
