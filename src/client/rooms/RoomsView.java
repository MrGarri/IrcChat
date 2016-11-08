package client.rooms;

import client.base.FrameView;
import common.Room;

import java.util.List;

public interface RoomsView extends FrameView<RoomsPresenter> {

    void setRoomsList(List<Room> roomsList);
}
