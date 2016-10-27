package server;

import javax.jms.JMSContext;

public interface Server {

    UsersManager getUsersManager();
    DestinationsManager getDestinations();
    JMSContext getContext();
    RoomsManager getRoomsManager();

}
