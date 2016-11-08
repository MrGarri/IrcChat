package client.login.impl;

import client.RequestCallback;
import client.base.Presenter;
import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
import client.rooms.impl.RoomsPresenterImpl;
import common.ServerResponse;
import common.User;
import common.impl.ErrorServerResponse;
import common.impl.LoginRequest;
import common.impl.RegisterRequest;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter, RequestCallback {

    @Override
    protected LoginView createView() {
        LoginView loginView = new LoginViewImpl();
        loginView.create(this);
        return loginView;
    }

    @Override
    public void onLogin() {
        LoginRequest request = new LoginRequest();
        request.setUsername(getView().getUser());
        request.setPassword(getView().getPassword());
        getClient().sendRequest(request, this);
    }

    @Override
    public void onRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername(getView().getUser());
        request.setPassword(getView().getPassword());
        getClient().sendRequest(request, this);
    }

    @Override
    public void success(ServerResponse response) {
        getClient().setUser(new User(getView().getUser(), getView().getPassword()));
        getView().close();

        Presenter roomsPresenter = new RoomsPresenterImpl();
        roomsPresenter.initialize(getClient());
    }

    @Override
    public void failure(ErrorServerResponse response) {
        getView().showError(response.getError());
    }
}
