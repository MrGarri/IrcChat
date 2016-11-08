package client;

import common.CommonDestinations;
import common.dto.User;
import common.messages.impl.BaseServerRequest;

public interface Client {
    void sendRequest(BaseServerRequest request, RequestCallback callback);

    void setUser(User user);

    CommonDestinations getDestinationsManager();
}
