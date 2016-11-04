package client.base.impl;

import client.Client;
import client.base.Presenter;
import client.base.View;

public abstract class BasePresenter<V extends View> implements Presenter<V> {

    V view;
    Client client;

    @Override
    public void initialize(Client client) {
        this.client = client;

        view = createView();
        view.initialize();
    }

    protected abstract V createView();

    @Override
    public V getView() {
        return view;
    }
}
