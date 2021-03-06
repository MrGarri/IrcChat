package client.base.impl;

import client.Client;
import client.base.FrameView;
import client.base.Presenter;
import client.base.View;

import java.lang.reflect.InvocationTargetException;

public abstract class BasePresenter<V extends View> implements Presenter<V> {

    public static <P extends BasePresenter> P createPresenter(Client client, Class<P> clazz){
        Exception ex;
        try {
            P presenter = clazz.getConstructor().newInstance();
            presenter.initialize(client);
            return presenter;
        } catch (InstantiationException e) {
            ex = e;
        } catch (IllegalAccessException e) {
            ex = e;
        } catch (InvocationTargetException e) {
            ex = e;
        } catch (NoSuchMethodException e) {
            ex = e;
        }

        throw new RuntimeException("Error thrown creating presenter", ex);
    }


    V view;
    Client client;

    @Override
    public void initialize(Client client) {
        this.client = client;

        view = createView();
        view.initialize();
    }

    public <P extends BasePresenter> P createPresenter(Class<P> clazz){
        return BasePresenter.createPresenter(getClient(), clazz);
    }

    protected abstract V createView();

    @Override
    public V getView() {
        return view;
    }

    public Client getClient() {
        return client;
    }

    public void finish(){
        if(getView() instanceof FrameView){
            ((FrameView) getView()).close();
        }

        view = null;
    }

}
