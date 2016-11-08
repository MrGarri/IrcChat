package client;

import common.CommonDestinations;
import common.messages.ServerRequest;
import common.dto.User;

public interface Client {
    void sendRequest(ServerRequest request, RequestCallback callback);

    void setUser(User user);

    CommonDestinations getDestinationsManager();
}
