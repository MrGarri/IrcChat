package server.impl;

import common.User;
import javafx.util.Pair;
import server.PetitionProcessor;
import server.Server;

import javax.jms.Destination;
import javax.jms.Message;

public class PetitionProcessorImpl implements PetitionProcessor {

    public Server server;

    public PetitionProcessorImpl(Server server) {
        this.server = server;
    }

    @Override
    public Pair<Destination, Message> register(String user, String password) {

        String message;

        if (server.getUsersManager().userExists(user)) {
            message = "User " + user + " already exists";
        } else {
            server.getUsersManager().addUser(user, password);
            message = "Successfully registered!";
        }

        return new Pair(server.getDestinations().getReplyDestination(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> login(String user, String password) {

        String message;

        if (server.getUsersManager().login(user, password)) {
            message = "Log in successful!";
        } else {
            message = "Failed to log in. Try again";
        }

        return new Pair(server.getDestinations().getReplyDestination(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> createRoom(String name, User user) {

        String message;

        if(server.getRoomsManager().roomExists(name)) {
            message = "Room " + name + " already exists";
        } else {
            server.getRoomsManager().addRoom(name, user);
            message = "Room successfully created!";
        }

        return new Pair(server.getDestinations().getRoomsTopic(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> removeRoom(String name, User user) {

        return null;

    }
}
