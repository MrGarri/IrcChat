package client;

import client.base.Presenter;
import client.login.impl.LoginPresenterImpl;
import common.*;
import common.dto.User;
import common.messages.ServerResponse;
import common.messages.impl.BaseServerRequest;

import javax.jms.*;
import java.lang.management.ManagementFactory;

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
    public void sendRequest(BaseServerRequest request, RequestCallback callback){
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
            RequestCallback current = callback;
            try {
                ServerResponse response = message.getBody(ServerResponse.class);
                if (response.wasSuccessful()) {
                    callback.success(response.getData());
                } else {
                    callback.failure((String) response.getData());
                }
            } catch (JMSException e) {
                callback.failure(null);
            }

            if(callback == current)
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
    public User getUser() {
        return loggedUser;
    }

    @Override
    public CommonDestinations getDestinationsManager() {
        return destinationsManager;
    }

    private void run(){
        responseQueue = context.createQueue("client_"+ManagementFactory.getRuntimeMXBean().getName().replace('@','-').replace('-','_'));
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
