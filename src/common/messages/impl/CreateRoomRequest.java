package common.messages.impl;

import java.io.Serializable;

public class CreateRoomRequest extends BaseServerRequest implements Serializable {

    String roomName;

    @Override
    public String getAction() {
        return "createRoom";
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
