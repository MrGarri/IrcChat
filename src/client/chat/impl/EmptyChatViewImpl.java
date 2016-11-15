package client.chat.impl;

import client.base.impl.BaseView;
import client.chat.EmptyChatPresenter;
import client.chat.EmptyChatView;

import javax.swing.*;
import java.awt.*;

public class EmptyChatViewImpl extends BaseView<EmptyChatPresenter> implements EmptyChatView {

    @Override
    protected void initializePanel(JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);

        JLabel noRooms = new JLabel("Select a room to start chatting.");
        noRooms.setHorizontalAlignment(SwingConstants.CENTER);
        noRooms.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(noRooms,BorderLayout.CENTER);
    }

}
