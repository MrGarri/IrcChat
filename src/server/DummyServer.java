package server;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

public class DummyServer {

    public static void main(String[] args){

        ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();

        try (JMSContext context = connectionFactory.createContext()){
            Queue queue = context.createQueue("Queue");
            Topic topic = context.createTopic("Topic");



            context.createProducer().send(queue, "Holi");

        }

    }

}
