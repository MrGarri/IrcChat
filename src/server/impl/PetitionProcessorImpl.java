package server.impl;

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

        String message = "";

        if (server.getUsersManager().userExists(user)) {
            message = "User already exists";
        } else {
            server.getUsersManager().addUser(user, password);
            message = "Successfully registered!";
        }

        return new Pair(server.getDestinations().getReplyDestination(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> login(String user, String password) {

        if(server.getUsersManager().login(user, password))
            return new Pair(server.getDestinations().getReplyDestination(),
                    server.getContext().createTextMessage("Log in successful!"));

        return new Pair(server.getDestinations().getReplyDestination(),
                server.getContext().createTextMessage("Failed to log in. Try again"));

    }

    @Override
    public Pair<Destination, Message> createRoom(String name, String user) {
        return null;
    }

    @Override
    public Pair<Destination, Message> removeRoom(String name, String user) {
        return null;
    }
}
