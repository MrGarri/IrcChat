package client.rooms;

import client.base.Presenter;
import common.Room;

public interface RoomsPresenter extends Presenter<RoomsView> {

    void createRoom(String roomName);

    void removeRoom(Room room);

    void selectRoom(Room room);
    
}
