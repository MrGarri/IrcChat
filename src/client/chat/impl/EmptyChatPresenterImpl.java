package client.chat.impl;

import client.base.impl.BasePresenter;
import client.chat.EmptyChatPresenter;
import client.chat.EmptyChatView;

public class EmptyChatPresenterImpl extends BasePresenter<EmptyChatView> implements EmptyChatPresenter {

    @Override
    protected EmptyChatView createView() {
        EmptyChatView emptyChatView = new EmptyChatViewImpl();
        emptyChatView.create(this);
        return emptyChatView;
    }

}
