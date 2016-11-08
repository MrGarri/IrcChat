package common.messages;

import java.io.Serializable;

public interface ServerResponse<T> extends Serializable {

    boolean wasSuccessful();
    T getData();

}
