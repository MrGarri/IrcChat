package client.chat.impl;

import client.base.impl.BaseView;
import client.chat.ChatPresenter;
import client.chat.ChatView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChatViewImpl extends BaseView<ChatPresenter> implements ChatView {

    private JEditorPane messages;
    private JTextField message;

    @Override
    protected void initializePanel(JPanel panel) {

        panel.setLayout(new BorderLayout());

        JPanel chatPanel = new JPanel (new BorderLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel writePanel = new JPanel(new BorderLayout());

        // infoPanel settings.
        JLabel roomName = new JLabel(getPresenter().getRoom().getName());
        Font boldFont = new Font(roomName.getFont().getFontName(), Font.BOLD, 14);
        roomName.setFont(boldFont);

        JButton leaveRoom = new JButton(new AbstractAction("Leave Room") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().leaveRoom();
            }
        });

        leaveRoom.setPreferredSize(new Dimension(150,10));
        roomName.setPreferredSize(new Dimension (0,35));

        infoPanel.add(roomName,BorderLayout.CENTER);
        infoPanel.add(leaveRoom,BorderLayout.EAST);


        // chatPanel settings.

        messages = new JEditorPane("text/html", "");
        Font messagesFont = new Font(roomName.getFont().getFontName(), Font.PLAIN, 14);
        messages.setFont(messagesFont);
        messages.setEditable(false);

        chatPanel.add(messages,BorderLayout.CENTER);

        // writePanel settings.
        message = new JTextField();
        message.addActionListener(e -> sendMessage());
        JButton sendMessage = new JButton(new AbstractAction("Send") {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        sendMessage.setPreferredSize(new Dimension(150,30));

        writePanel.add(message,BorderLayout.CENTER);
        writePanel.add(sendMessage,BorderLayout.EAST);

        // conversationPanel settings.
        panel.add(infoPanel,BorderLayout.NORTH);
        panel.add(chatPanel,BorderLayout.CENTER);
        panel.add(writePanel,BorderLayout.SOUTH);

        panel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                message.requestFocus();
            }
        });
    }

    @Override
    public void clearConversation() {
        messages.setText("");
    }

    @Override
    public void addMessage(String user, String message) {
        messages.setText(messages.getText()+"<b>"+user+"</b>: "+message+"<br/>");
    }

    private void sendMessage() {
        getPresenter().sendMessage(message.getText());
        message.setText("");
    }

}
