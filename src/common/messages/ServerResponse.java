package common.messages;

import java.io.Serializable;

public interface ServerResponse extends Serializable {

    boolean wasSuccessful();

}
