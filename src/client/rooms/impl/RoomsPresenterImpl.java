package client.rooms.impl;

import client.Client;
import client.RequestCallback;
import client.base.Presenter;
import client.base.impl.BasePresenter;
import client.chat.ChatPresenter;
import client.chat.EmptyChatPresenter;
import client.chat.EmptyChatView;
import client.chat.impl.ChatPresenterImpl;
import client.chat.impl.EmptyChatPresenterImpl;
import client.chat.impl.EmptyChatViewImpl;
import client.login.impl.LoginPresenterImpl;
import client.rooms.RoomsPresenter;
import client.rooms.RoomsView;
import common.dto.Room;
import common.messages.impl.BaseServerResponse;
import common.messages.impl.CreateRoomRequest;
import common.messages.impl.GetRoomsRequest;
import common.messages.impl.RemoveRoomRequest;

import javax.jms.JMSException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomsPresenterImpl extends BasePresenter<RoomsView> implements RoomsPresenter, ChatPresenterImpl.OnLeaveRoomListener {

    EmptyChatPresenter emptyPresenter;
    Map<Room, ChatPresenter> chatPresenters = new HashMap<>();

    @Override
    public void initialize(Client client) {
        super.initialize(client);

        emptyPresenter = createPresenter(EmptyChatPresenterImpl.class);
        getView().setChatView(emptyPresenter.getView());

        // Setup listener for room changes from server
        client.getContext().createConsumer(client.getDestinationsManager().getRoomsTopic()).setMessageListener(message -> {
            try { onRoomsMessage((List<Room>) message.getBody(BaseServerResponse.class).getData()); } catch (JMSException e) {
                    e.printStackTrace();
                }
        });

        // Request current rooms
        getClient().sendRequest(new GetRoomsRequest(), new RequestCallback<List<Room>>() {
            @Override
            public void success(List<Room> response) {
                onRoomsMessage(response);
            }

            @Override
            public void failure(String error) {
                getView().showError(error);
            }
        });
    }

    @Override
    protected RoomsView createView() {
        RoomsView roomsView = new RoomsViewImpl();
        roomsView.create(this);
        return roomsView;
    }

    @Override
    public void createRoom(String roomName){
        CreateRoomRequest request = new CreateRoomRequest();
        request.setRoomName(roomName);
        getClient().sendRequest(request, new RequestCallback() {
            @Override
            public void success(Object response) {}

            @Override
            public void failure(String error) {
                getView().showError(error);
            }
        });
    }

    @Override
    public void removeRoom(Room room){
        RemoveRoomRequest request = new RemoveRoomRequest();
        request.setRoomName(room.getName());
        getClient().sendRequest(request, new RequestCallback() {
            @Override
            public void success(Object response) {}

            @Override
            public void failure(String error) {
                getView().showError(error);
            }
        });
    }

    @Override
    public void selectRoom(Room room){
        getView().setTitle(room.getName());
        getView().setChatView(getChatPresenter(room).getView());
    }

    private ChatPresenter getChatPresenter(Room room){
        ChatPresenter chatPresenter = chatPresenters.get(room);
        if(chatPresenter == null){
            chatPresenter = new ChatPresenterImpl(room, this);
            chatPresenter.initialize(getClient());
            chatPresenters.put(room, chatPresenter);
        }

        return chatPresenter;
    }

    private void onRoomsMessage(List<Room> roomList){
        getView().setRoomsList(roomList);
    }

    @Override
    public void onLogout(){
        createPresenter(LoginPresenterImpl.class);
        finish();
    }

    @Override
    public void onLeaveRoom(Room room) {
        chatPresenters.remove(room).finish();
        
        getView().setTitle(null);
        getView().setChatView(emptyPresenter.getView());
    }

    @Override
    public void finish() {
        emptyPresenter.finish();
        super.finish();
    }
}
