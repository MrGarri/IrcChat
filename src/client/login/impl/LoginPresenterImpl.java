package client.login.impl;

import client.RequestCallback;
import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
import common.messages.ServerResponse;
import common.dto.User;
import common.messages.impl.ErrorServerResponse;
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
    public void success(ServerResponse response) {
        getClient().setUser(new User(getView().getUser(), getView().getPassword()));
        getView().close();
    }

    @Override
    public void failure(ErrorServerResponse response) {
        getView().showError(response.getError());
    }
}
