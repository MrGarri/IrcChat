package client.chat.impl;

import client.base.impl.BasePresenter;
import client.chat.ChatPresenter;
import client.chat.ChatView;

public abstract class ChatPresenterImpl extends BasePresenter<ChatView> implements ChatPresenter {

    @Override
    protected ChatView createView() {
        ChatView chatView = new ChatViewImpl();
        chatView.create(this);
        return chatView;
    }

    @Override
    public void sendMessage(String message) {

    }
    
}
