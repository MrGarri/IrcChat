package server.impl;

import server.DestinationsManager;
import server.RoomsManager;
import server.Server;
import server.UsersManager;

import javax.jms.JMSContext;

public class ServerImpl implements Server {

    private DestinationsManager destinationsManager;
    private RoomsManager roomsManager;
    private UsersManager usersManager;
    private JMSContext context;

    public ServerImpl(DestinationsManager destinationsManager, RoomsManager roomsManager,
                      UsersManager usersManager, JMSContext context) {
        this.destinationsManager = destinationsManager;
        this.roomsManager = roomsManager;
        this.usersManager = usersManager;
        this.context = context;
    }

    @Override
    public UsersManager getUsersManager() {
        return usersManager;
    }

    @Override
    public DestinationsManager getDestinations() {
        return destinationsManager;
    }

    @Override
    public JMSContext getContext() {
        return context;
    }

    @Override
    public RoomsManager getRoomsManager() {
        return roomsManager;
    }

    public static void main(String[] args) {



    }

}
