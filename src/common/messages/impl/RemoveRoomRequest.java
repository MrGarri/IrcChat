package common.messages.impl;

import java.io.Serializable;

public class RemoveRoomRequest extends BaseServerRequest implements Serializable{

    String roomName;

    @Override
    public String getAction() {
        return "removeRoom";
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
