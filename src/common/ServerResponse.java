package common;

import java.util.Map;

public interface ServerResponse {

    boolean wasSuccessful();
    Map<String, Object> getParams();

}
