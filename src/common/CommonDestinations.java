package common;

import javax.jms.Queue;
import javax.jms.Topic;

public interface CommonDestinations {

    String REQUESTS = "REQUESTS";

    Queue getRequestsQueue();

    Topic getRoomsTopic();

}
