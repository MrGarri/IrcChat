package client;

import common.CommonDestinations;
import common.ServerRequest;
import common.User;

import javax.jms.JMSContext;

public interface Client {
    void sendRequest(ServerRequest request, RequestCallback callback);

    JMSContext getContext();

    void setUser(User user);

    CommonDestinations getDestinationsManager();
}
