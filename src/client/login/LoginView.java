package client.login;

import client.base.View;

public interface LoginView extends View<LoginPresenter> {

    String getUser();
    String getPassword();
    void showError(String error);
    void close();

}
