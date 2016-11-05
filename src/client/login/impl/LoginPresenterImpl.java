package client.login.impl;

import client.RequestCallback;
import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
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
        getClient().sendRequest(new LoginRequest(), this);
    }

    @Override
    public void onRegister() {
        getClient().sendRequest(new RegisterRequest(), this);
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
