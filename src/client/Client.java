package client;

import common.CommonDestinations;
import common.ServerRequest;

public interface Client {
    void makeRequest(ServerRequest request, RequestCallback callback);
    CommonDestinations getDestinationsManager();
}
