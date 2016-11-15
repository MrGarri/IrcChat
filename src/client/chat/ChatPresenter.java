package client.chat;

import client.base.Presenter;
import common.dto.Room;

public interface ChatPresenter extends Presenter<ChatView> {

    void leaveRoom();
    void sendMessage(String message);
    Room getRoom();

}
