package client;

import client.base.Presenter;
import client.login.impl.LoginPresenterImpl;
import common.CommonDestinations;
import common.DestinationsManager;
import common.ServerRequest;
import common.ServerResponse;

import javax.jms.*;

public class ClientImpl implements Client, MessageListener {

    private JMSContext context;
    private CommonDestinations destinationsManager;

    private Queue responseQueue;
    private RequestCallback callback;
    private User loggedUser;

    public ClientImpl(JMSContext context){
        this.context = context;
        destinationsManager = new DestinationsManager(context);
    }

    @Override
    public void makeRequest(ServerRequest request, RequestCallback callback){
        try {
            ObjectMessage message = context.createObjectMessage();
            message.setObject(request);

            context.createProducer().send(getDestinationsManager().getRequestsQueue(), message);
            this.callback = callback;
        } catch (JMSException e) {
            callback.failure(null);
        }
    }

    @Override
    public void onMessage(Message message) {
        if(callback != null) {
            try {
                ServerResponse response = message.getBody(ServerResponse.class);
                if (response.wasSuccessful()) {
                    callback.success(response);
                } else {
                    callback.failure(response);
                }
            } catch (JMSException e) {
                callback.failure(null);
            }

            callback = null;
        }
    }

    @Override
    public CommonDestinations getDestinationsManager() {
        return destinationsManager;
    }

    private void run(){
        responseQueue = context.createQueue(System.getProperty("user.name"));
        context.createConsumer(responseQueue).setMessageListener(this);

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
