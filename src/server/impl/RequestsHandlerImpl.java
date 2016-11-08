package server.impl;

import common.messages.ServerRequest;
import common.messages.ServerResponse;
import common.messages.impl.*;
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

        ServerResponse response;

        if (server.getUsersManager().userExists(username)) {
            response = new ErrorServerResponse("User " + username + " already exists");
        } else {
            server.getUsersManager().addUser(username, password);
            response = new BaseServerResponse(true);
        }

        return new Pair(server.getReplyDestination(),
                server.getContext().createObjectMessage(response));

    }

    @Override
    public Pair<Destination, Message> login(ServerRequest request) {

        LoginRequest loginRequest = (LoginRequest)request;

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        ServerResponse response;

        if (server.getUsersManager().login(username, password)) {
            response = new BaseServerResponse(true);
        } else {
            response = new ErrorServerResponse("Failed to log in. Try again");
        }

        return new Pair(server.getReplyDestination(),
                server.getContext().createObjectMessage(response));

    }

    @Override
    public Pair<Destination, Message> createRoom(ServerRequest request) {

        CreateRoomRequest createRoomRequest = (CreateRoomRequest)request;

        String roomName = createRoomRequest.getRoomName();

        ServerResponse response;

        if(server.getRoomsManager().roomExists(roomName)) {
            response = new ErrorServerResponse("Room " + roomName + " already exists");
        } else {
            server.getRoomsManager().addRoom(roomName, createRoomRequest.getUser());
            response = new BaseServerResponse(true);
        }

        return new Pair(server.getDestinations().getRoomsTopic(),
                server.getContext().createObjectMessage(response));

    }

    @Override
    public Pair<Destination, Message> removeRoom(ServerRequest request) {

        RemoveRoomRequest removeRoomRequest = (RemoveRoomRequest) request;

        String roomName = removeRoomRequest.getRoomName();

        ServerResponse response;

        if(server.getRoomsManager().roomExists(roomName)) {
            if(server.getRoomsManager().isOwner(roomName, removeRoomRequest.getUser())) {
                server.getRoomsManager().removeRoom(roomName);
                response = new BaseServerResponse(true);
            } else {
                response = new ErrorServerResponse("You're not the owner of the room " + roomName);
            }
        } else {
            response = new ErrorServerResponse("Room " + roomName + " doesn't exists");
        }

        return new Pair(server.getDestinations().getRoomsTopic(),
                server.getContext().createObjectMessage(response));

    }
}
