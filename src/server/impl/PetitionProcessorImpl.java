package server.impl;

import com.sun.messaging.jmq.jmsclient.JMSContextImpl;
import com.sun.messaging.jmq.jmsclient.MessageConsumerImpl;
import com.sun.messaging.jmq.jmsclient.ObjectMessageImpl;
import com.sun.messaging.jmq.jmsclient.TextMessageImpl;
import javafx.util.Pair;
import server.PetitionProcessor;
import server.Server;
import sun.plugin2.message.TextEventMessage;

import javax.jms.Destination;
import javax.jms.Message;

public class PetitionProcessorImpl implements PetitionProcessor {

    public Server server;

    public PetitionProcessorImpl(Server server) {
        this.server = server;
    }

    @Override
    public Pair<Destination, Message> register(String user, String password) {

        if (server.getUsersManager().userExists(user)) {
            return new Pair(server.getDestinations().getReplyDestination(),
                    server.getContext().createTextMessage("User already exists"));
        }

        return new Pair(server.getDestinations().getReplyDestination(),
                server.getContext().createTextMessage("Succesfully registered!"));

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
