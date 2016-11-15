package client.rooms;

import client.base.FrameView;
import client.base.View;
import common.dto.Room;

import java.util.List;

public interface RoomsView extends FrameView<RoomsPresenter> {

    void setRoomsList(List<Room> roomsList);
    void setChatView(View view);
    void setTitle(String title);

    void clearRoomSelection();
}
