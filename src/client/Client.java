package client;

import client.base.Presenter;
import client.login.impl.LoginPresenterImpl;

public class Client {


    public static void main(String[] args){

        Presenter presenter = new LoginPresenterImpl();
        presenter.initialize();

    }

}
