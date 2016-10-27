package client.base;

public abstract class BaseView<P extends Presenter> implements View<P> {

    P presenter;

    @Override
    public void create(P presenter) {
        this.presenter = presenter;
    }

    public P getPresenter() {
        return presenter;
    }
}
