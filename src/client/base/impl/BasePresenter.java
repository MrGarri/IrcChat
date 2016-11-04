package client.base.impl;

import client.base.Presenter;
import client.base.View;

public abstract class BasePresenter<V extends View> implements Presenter<V> {

    V view;

    @Override
    public void initialize() {
        view = createView();
        view.initialize();
    }

    protected abstract V createView();

    @Override
    public V getView() {
        return view;
    }
}
