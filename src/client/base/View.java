package client.base;

public interface View <P extends Presenter>{

    void create(P presenter);
    void initialize();

}
