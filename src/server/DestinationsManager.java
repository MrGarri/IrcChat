package server;

import common.CommonDestinations;

import javax.jms.Destination;
import javax.jms.Topic;


public class DestinationsManager implements CommonDestinations {
    @Override
    public Topic getRoomsTopic() {
        //TODO
        return null;
    }

    public Destination getReplyDestination(){
        //TODO
        return null;
    }
}
