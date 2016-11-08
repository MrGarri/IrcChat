package common;

import javax.jms.Queue;
import javax.jms.Topic;

public interface CommonDestinations {

    String REQUESTS = "REQUESTS";
    String ROOMS = "ROOMS";

    Queue getRequestsQueue();

    Topic getRoomsTopic();

}
