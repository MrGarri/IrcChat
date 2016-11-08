package server;

import common.messages.ServerRequest;
import javafx.util.Pair;

import javax.jms.Destination;
import javax.jms.Message;

public interface RequestsHandler {

    Pair<Destination, Message> register(ServerRequest request);

    Pair<Destination, Message> login(ServerRequest request);

    Pair<Destination, Message> createRoom(ServerRequest request);

    Pair<Destination, Message> removeRoom(ServerRequest request);

    Pair<Destination, Message> getRooms(ServerRequest request);
}
