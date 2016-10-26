package client;

import javax.jms.*;

public class DummyClient {

    public static void main(String[] args){

        ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();

        try (JMSContext context = connectionFactory.createContext()){
            Queue queue = context.createQueue("Queue");
            JMSConsumer consumer = context.createConsumer(queue);

            consumer.setMessageListener(message -> {
                try {
                    System.out.println( ((TextMessage)message).getText() );
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
