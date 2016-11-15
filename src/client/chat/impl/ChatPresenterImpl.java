package client.chat.impl;

import client.Client;
import client.base.impl.BasePresenter;
import client.chat.ChatPresenter;
import client.chat.ChatView;
import client.chat.ConversationText;
import common.dto.Room;

import javax.jms.*;

public class ChatPresenterImpl extends BasePresenter<ChatView> implements ChatPresenter, MessageListener {

    private Room room;
    private Topic roomTopic;
    private OnLeaveRoomListener listener;
    private JMSConsumer consumer;

    public ChatPresenterImpl(Room room, OnLeaveRoomListener listener){
        this.room = room;
        this.listener = listener;
    }

    @Override
    protected ChatView createView() {
        ChatView chatView = new ChatViewImpl();
        chatView.create(this);
        return chatView;
    }

    @Override
    public void initialize(Client client) {
        super.initialize(client);
        roomTopic = client.getDestinationsManager().getRoomTopic(room);
        consumer = client.getContext().createSharedDurableConsumer(roomTopic, getClient().getClientID()+room.getName());
        consumer.setMessageListener(this);
    }

    @Override
    public void leaveRoom() {
        listener.onLeaveRoom(room);
    }

    @Override
    public void sendMessage(String message) {
        ConversationText conversationText = new ConversationText();
        conversationText.setUser(getClient().getUser());
        conversationText.setText(message);

        getClient().getContext().createProducer().send(roomTopic, conversationText);
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void onMessage(Message message) {
        try {
            ConversationText conversationText = message.getBody(ConversationText.class);
            getView().addMessage(conversationText.getUser().getUser(), conversationText.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        consumer.close();
        super.finish();
    }

    public interface OnLeaveRoomListener {
        void onLeaveRoom(Room room);
    }
}
