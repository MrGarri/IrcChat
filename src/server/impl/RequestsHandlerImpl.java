package server.impl;

import common.ServerRequest;
import common.impl.CreateRoomRequest;
import common.impl.LoginRequest;
import common.impl.RegisterRequest;
import common.impl.RemoveRoomRequest;
import javafx.util.Pair;
import server.RequestsHandler;
import server.Server;

import javax.jms.Destination;
import javax.jms.Message;

public class RequestsHandlerImpl implements RequestsHandler {

    public Server server;

    public RequestsHandlerImpl(Server server) {
        this.server = server;
    }

    @Override
    public Pair<Destination, Message> register(ServerRequest request) {

        RegisterRequest registerRequest = (RegisterRequest) request;

        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        String message;

        if (server.getUsersManager().userExists(username)) {
            message = "User " + username + " already exists";
        } else {
            server.getUsersManager().addUser(username, password);
            message = "Successfully registered!";
        }

        return new Pair(server.getReplyDestination(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> login(ServerRequest request) {

        LoginRequest loginRequest = (LoginRequest)request;

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        String message;

        if (server.getUsersManager().login(username, password)) {
            message = "Log in successful!";
        } else {
            message = "Failed to log in. Try again";
        }

        return new Pair(server.getReplyDestination(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> createRoom(ServerRequest request) {

        CreateRoomRequest createRoomRequest = (CreateRoomRequest)request;

        String roomName = createRoomRequest.getRoomName();

        String message;

        if(server.getRoomsManager().roomExists(roomName)) {
            message = "Room " + roomName + " already exists";
        } else {
            server.getRoomsManager().addRoom(roomName, createRoomRequest.getUser());
            message = "Room successfully created!";
        }

        return new Pair(server.getDestinations().getRoomsTopic(),
                server.getContext().createTextMessage(message));

    }

    @Override
    public Pair<Destination, Message> removeRoom(ServerRequest request) {

        RemoveRoomRequest removeRoomRequest = (RemoveRoomRequest) request;

        String roomName = removeRoomRequest.getRoomName();

        String message;

        if(server.getRoomsManager().roomExists(roomName)) {
            if(server.getRoomsManager().isOwner(roomName, removeRoomRequest.getUser())) {
                server.getRoomsManager().removeRoom(roomName);
                message = "Room " + roomName + " successfully removed";
            } else {
                message = "You're not the owner of the room " + roomName;
            }
        } else {
            message = "Room " + roomName + " doesn't exists";
        }

        return new Pair(server.getDestinations().getRoomsTopic(),
                server.getContext().createTextMessage(message));

    }
}
