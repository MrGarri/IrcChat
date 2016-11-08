package server;

import common.dto.Room;
import common.dto.User;

public interface RoomsManager {

    boolean roomExists(String name);
    Room addRoom(String name, User user);
    boolean isOwner(String name, User user);
    Room removeRoom(String name);

}
