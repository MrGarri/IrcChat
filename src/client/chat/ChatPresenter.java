package client.chat;

import client.base.Presenter;

public interface ChatPresenter extends Presenter<ChatView> {

    void leaveRoom();
    void sendMessage(String message);

}
