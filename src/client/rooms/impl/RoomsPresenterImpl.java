package client.rooms.impl;

import client.Client;
import client.RequestCallback;
import client.base.Presenter;
import client.base.impl.BasePresenter;
import client.chat.ChatPresenter;
import client.chat.impl.ChatPresenterImpl;
import client.login.impl.LoginPresenterImpl;
import client.rooms.RoomsPresenter;
import client.rooms.RoomsView;
import common.dto.Room;
import common.messages.impl.BaseServerResponse;
import common.messages.impl.CreateRoomRequest;
import common.messages.impl.GetRoomsRequest;
import common.messages.impl.RemoveRoomRequest;

import javax.jms.JMSException;
import java.util.List;

public class RoomsPresenterImpl extends BasePresenter<RoomsView> implements RoomsPresenter {

    @Override
    public void initialize(Client client) {
        super.initialize(client);

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

        ChatPresenter chatPresenter = new ChatPresenterImpl() {
            @Override
            public void leaveRoom() {
                //TODO
            }
        };

        getView().setChatView(chatPresenter.getView());
    }

    private void onRoomsMessage(List<Room> roomList){
        getView().setRoomsList(roomList);
    }

    @Override
    public void onLogout(){
        getView().close();
        Presenter presenter = new LoginPresenterImpl();
        presenter.initialize(this.getClient());
    }

}
