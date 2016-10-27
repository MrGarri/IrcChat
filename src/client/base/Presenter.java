package client.base;

public interface Presenter <V extends View>{

    void initialize();
    V getView();

}
