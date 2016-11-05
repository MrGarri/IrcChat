package common.impl;

import common.User;

public class CreateRoomRequest extends BaseServerRequest {

    public CreateRoomRequest(String roomName) {
        this.params.put("roomName", roomName);
    }

    @Override
    public String getAction() {
        return "createRoom";
    }

}
