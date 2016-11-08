package client;

import client.base.Presenter;
import client.login.impl.LoginPresenterImpl;
import common.*;
import common.impl.ErrorServerResponse;

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
    public void sendRequest(ServerRequest request, RequestCallback callback){
        try {
            request.setUser(loggedUser);
            ObjectMessage message = context.createObjectMessage();
            message.setObject(request);
            message.setJMSReplyTo(responseQueue);

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
                    callback.failure(message.getBody(ErrorServerResponse.class));
                }
            } catch (JMSException e) {
                callback.failure(null);
            }

            callback = null;
        }
    }

    @Override
    public JMSContext getContext(){
        return context;
    }

    @Override
    public void setUser(User user) {
        this.loggedUser = user;
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
        ClientImpl client = new ClientImpl(connectionFactory.createContext());
        client.run();
    }

}
