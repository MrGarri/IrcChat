package client.rooms.impl;

import client.Client;
import client.base.impl.BasePresenter;
import client.rooms.RoomsPresenter;
import client.rooms.RoomsView;
import common.Room;
import common.ServerResponse;

import javax.jms.JMSException;

public class RoomsPresenterImpl extends BasePresenter<RoomsView> implements RoomsPresenter {

    @Override
    public void initialize(Client client) {
        super.initialize(client);

        // Setup listener for room changes from server
        client.getContext().createConsumer(client.getDestinationsManager().getRoomsTopic()).setMessageListener(message -> {
            try { onRoomsMessage(message.getBody(ServerResponse.class)); } catch (JMSException e) {
                    e.printStackTrace();
                }
        });
    }

    @Override
    protected RoomsView createView() {
        return new RoomsViewImpl();
    }

    @Override
    public void createRoom(String roomName){

    }

    @Override
    public void removeRoom(Room room){

    }

    @Override
    public void selectRoom(Room room){

    }

    private void onRoomsMessage(ServerResponse serverResponse){

    }

}
