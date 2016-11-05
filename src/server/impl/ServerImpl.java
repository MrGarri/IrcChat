package server.impl;

import common.DestinationsManager;
import common.ServerRequest;
import javafx.util.Pair;
import server.RequestsHandler;
import server.RoomsManager;
import server.Server;
import server.UsersManager;

import javax.jms.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class ServerImpl implements Server {

    private JMSContext context;

    private DestinationsManager destinationsManager;
    private RoomsManager roomsManager;
    private UsersManager usersManager;
    private RequestsHandler requestsHandler;
    private Destination replyDestination;

    public ServerImpl(JMSContext context) {
        this.context = context;

        this.destinationsManager = new DestinationsManager(context);
        this.roomsManager = new RoomsManagerImpl(this);
        this.usersManager = new UsersManagerImpl(this);
        this.requestsHandler = new RequestsHandlerImpl(this);
    }

    private void run() {
        JMSConsumer requestsConsumer = context.createConsumer(destinationsManager.getRequestsQueue());
        while(true){
            Message message = requestsConsumer.receive();

            //TODO: Get request
            //String request = "login";

            try {
                replyDestination = message.getJMSReplyTo();
                ServerRequest request = message.getBody(ServerRequest.class);

                Method method = requestsHandler.getClass().getMethod(request.getAction());
                Pair<Destination, Message> answer = (Pair<Destination, Message>) method.invoke(requestsHandler, request);

                context.createProducer().send(answer.getKey(), answer.getValue());

            } catch (JMSException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            replyDestination = null;

        }
    }

    @Override
    public UsersManager getUsersManager() {
        return usersManager;
    }

    @Override
    public DestinationsManager getDestinations() {
        return destinationsManager;
    }

    @Override
    public JMSContext getContext() {
        return context;
    }

    @Override
    public RoomsManager getRoomsManager() {
        return roomsManager;
    }

    @Override
    public Object getReplyDestination() {
        return replyDestination;
    }

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
        try(JMSContext context = connectionFactory.createContext()){
            ServerImpl server = new ServerImpl(context);
            server.run();
        }
    }

}
