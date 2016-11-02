package server.impl;

import common.Room;
import common.User;
import server.RoomsManager;
import server.Server;

import java.util.HashMap;
import java.util.Map;

public class RoomsManagerImpl implements RoomsManager {

    Map<String, Room> rooms = new HashMap<>();
    Server server;

    public RoomsManagerImpl(Server server) {
        this.server = server;
    }

    @Override
    public boolean roomExists(String name) {
        return rooms.containsKey(name);
    }

    @Override
    public Room addRoom(String name, User user) {

        Room room = new Room(name, user);
        rooms.put(name, room);

        return room;

    }

    @Override
    public boolean isOwner(String name, User user) {

        Room room = rooms.get(name);

        return room.getUser().equals(user);

    }

    @Override
    public Room removeRoom(String name) {

        Room room = rooms.get(name);

        return rooms.remove(name);

    }
}
