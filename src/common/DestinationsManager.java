package common;

import common.CommonDestinations;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;


public class DestinationsManager implements CommonDestinations {

    JMSContext context;

    Queue requestsQueue;

    public DestinationsManager(JMSContext context){
        this.context = context;
    }

    @Override
    public Queue getRequestsQueue(){
        if(requestsQueue == null)
            context.createQueue(REQUESTS);

        return requestsQueue;
    }

    @Override
    public Topic getRoomsTopic() {
        //TODO
        return null;
    }

}
