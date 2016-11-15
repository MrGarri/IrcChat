package client.login.impl;

import client.RequestCallback;
import client.base.Presenter;
import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
import client.rooms.impl.RoomsPresenterImpl;
import common.dto.User;
import common.messages.impl.LoginRequest;
import common.messages.impl.RegisterRequest;

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
    public void success(Object response) {
        getClient().setUser(new User(getView().getUser(), getView().getPassword()));
        getView().close();

        createPresenter(RoomsPresenterImpl.class);
        finish();
    }

    @Override
    public void failure(String error) {
        getView().showError(error);
    }
}
