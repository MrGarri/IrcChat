package common;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;


public class DestinationsManager implements CommonDestinations {

    JMSContext context;

    Queue requestsQueue;
    Topic roomsTopic;

    public DestinationsManager(JMSContext context){
        this.context = context;
    }

    @Override
    public Queue getRequestsQueue(){
        if(requestsQueue == null)
            requestsQueue = context.createQueue(REQUESTS);

        return requestsQueue;
    }

    @Override
    public Topic getRoomsTopic() {
        if(roomsTopic == null)
            roomsTopic = context.createTopic(ROOMS);

        return roomsTopic;
    }

}
