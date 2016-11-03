package client.login.impl;

import client.base.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    @Override
    protected LoginView createView() {
        return new LoginViewImpl();
    }

    @Override
    public void onLogin() {
        //TODO
    }

    @Override
    public void onRegister() {
        //TODO
    }
}
