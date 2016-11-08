package client.rooms.impl;

import client.base.impl.BasePresenter;
import client.rooms.RoomsPresenter;
import client.rooms.RoomsView;
import common.Room;

public class RoomsPresenterImpl extends BasePresenter<RoomsView> implements RoomsPresenter {

    @Override
    protected RoomsView createView() {
        RoomsView roomsView = new RoomsViewImpl();
        roomsView.create(this);
        return roomsView;
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

}
