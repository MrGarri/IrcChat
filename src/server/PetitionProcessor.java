package server;

import javafx.util.Pair;

import javax.jms.Destination;
import javax.jms.Message;

public interface PetitionProcessor {

    Pair register(String user, String password);

    Pair<Destination, Message> login(String user, String password);

    Pair<Destination, Message> createRoom(String name, String user);

    Pair<Destination, Message> removeRoom(String name, String user);

}
