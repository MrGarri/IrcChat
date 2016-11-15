package client;

import common.CommonDestinations;
import common.dto.User;
import common.messages.impl.BaseServerRequest;

import javax.jms.JMSContext;

public interface Client {
    void sendRequest(BaseServerRequest request, RequestCallback callback);

    JMSContext getContext();

    void setUser(User user);

    User getUser();

    CommonDestinations getDestinationsManager();

    String getClientID();
}
