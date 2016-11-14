package client.chat;

import client.base.View;

public interface ChatView extends View<ChatPresenter> {

    void clearConversation();
    void addMessage(String user, String message);

}
