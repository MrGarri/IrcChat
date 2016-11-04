package client;

import client.base.Presenter;
import client.login.impl.LoginPresenterImpl;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;

public class ClientImpl implements Client {

    JMSContext context;

    public ClientImpl(JMSContext context){
        this.context = context;
    }

    private void run(){
        Presenter presenter = new LoginPresenterImpl();
        presenter.initialize(this);
    }

    public static void main(String[] args){
        ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = connectionFactory.createContext()) {
            ClientImpl client = new ClientImpl(context);
            client.run();
        }
    }

}
