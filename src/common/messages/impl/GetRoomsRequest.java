package common.messages.impl;

public class GetRoomsRequest extends BaseServerRequest {
    @Override
    public String getAction() {
        return "getRooms";
    }
}
