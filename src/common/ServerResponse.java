package common;

import java.io.Serializable;
import java.util.Map;

public interface ServerResponse extends Serializable {

    boolean wasSuccessful();

}
