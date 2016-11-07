package client;

import common.CommonDestinations;
import common.ServerRequest;
import common.User;

public interface Client {
    void sendRequest(ServerRequest request, RequestCallback callback);

    void setUser(User user);

    CommonDestinations getDestinationsManager();
}
