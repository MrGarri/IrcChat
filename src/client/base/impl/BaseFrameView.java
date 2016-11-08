package client.base.impl;

import client.base.FrameView;
import client.base.Presenter;

import javax.swing.*;

public abstract class BaseFrameView<P extends Presenter> extends BaseView<P> implements FrameView<P> {

    private JFrame frame;

    @Override
    public void initialize() {
        frame = new JFrame(getTitle());
        initializeFrame(frame);
        frame.add(getPanel());

        javax.swing.SwingUtilities.invokeLater(() -> {
            super.initialize();
        });
    }

    protected abstract void initializeFrame(JFrame frame);

    public abstract String getTitle();

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void close(){
        frame.dispose();
    }
}
