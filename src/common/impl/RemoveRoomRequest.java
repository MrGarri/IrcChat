package common.impl;

import common.ServerRequest;
import common.User;

public class RemoveRoomRequest extends BaseServerRequest {

    public RemoveRoomRequest(String roomName) {
        this.params.put("roomName", roomName);
    }

    @Override
    public String getAction() {
        return "removeRoom";
    }

}
