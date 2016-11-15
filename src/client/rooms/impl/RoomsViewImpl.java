package client.rooms.impl;

import client.base.View;
import client.base.impl.BaseFrameView;
import client.rooms.RoomsPresenter;
import client.rooms.RoomsView;
import common.dto.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RoomsViewImpl extends BaseFrameView<RoomsPresenter> implements RoomsView, ListSelectionListener {

    private JList<Room> jRoomsList;
    private JPanel chatPanel;
    private JSplitPane splitPane;

    public RoomsViewImpl(){
        jRoomsList = new JList<>();
    }

    @Override
    public String getTitle() {
        return "SwaggaIRC";
    }

    @Override
    protected void initializeFrame(JFrame frame) {

        // Set graphics settings, like size and position.
        frame.setSize(800,600);
        frame.setPreferredSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set options to the menuBar.
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem aboutUsItem = new JMenuItem(new AbstractAction("About us") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "About SwaggaIRC\n\nCreated by @Frildoren, @Garri23_23 & @diegofpb\nand coded with ❤ for Middleware [ETSIINF UPM]\nYear 2016",
                        "About us",
                        JOptionPane.INFORMATION_MESSAGE
                );

            }
        });

        JMenuItem logOutItem = new JMenuItem(new AbstractAction("Logout") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onLogout();
            }
        });

        fileMenu.add(aboutUsItem);
        fileMenu.add(logOutItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Show the frame.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void initializePanel(JPanel panel) {

        //Create the panel for the right side.
        panel.setLayout(new BorderLayout());

        //Provide size pane.
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setSize(new Dimension(800, 600));

        JPanel rightPanel = new JPanel(new BorderLayout());

        //Create the jRoomsList from availableRooms and put it in a scrollPane.
        jRoomsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jRoomsList.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(jRoomsList);

        JButton createRoom = new JButton(new AbstractAction("Create Room") {
            public void actionPerformed(ActionEvent e) {
                String roomName = JOptionPane.showInputDialog(
                        chatPanel,
                        "Enter the name of the room to continue",
                        "Room name needed",
                        JOptionPane.WARNING_MESSAGE
                );

                getPresenter().createRoom(roomName);
            }
        });

        // Right mouse click event listener.
        jRoomsList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if ( SwingUtilities.isRightMouseButton(e) ) {
                    jRoomsList.setSelectedIndex(jRoomsList.locationToIndex(e.getPoint()));

                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem itemRemove = new JMenuItem("Delete Room");
                    menu.add(itemRemove);
                    menu.show(jRoomsList, e.getPoint().x, e.getPoint().y);

                    itemRemove.addActionListener(e1 ->
                        deleteRoomConfirmation(jRoomsList.getSelectedValue())
                    );
                }
            }
        });

        rightPanel.add(jRoomsList, BorderLayout.CENTER);
        rightPanel.add(createRoom, BorderLayout.SOUTH);


        //Create the chatPanel, now with only a JText inside. (RIGHT SIDE)
        chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBackground(Color.lightGray);

        JLabel noRooms = new JLabel("Select a room to start chatting.");
        noRooms.setHorizontalAlignment(SwingConstants.CENTER);
        noRooms.setVerticalAlignment(SwingConstants.CENTER);
        chatPanel.add(noRooms,BorderLayout.CENTER);


        //Create a split pane with the two panels in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                rightPanel, chatPanel);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);


        //Provide minimum sizes for the two components in the split pane.
        Dimension listMinimumSize = new Dimension(200,50);
        Dimension chatMinimumSize = new Dimension(500,50);
        listScrollPane.setMinimumSize(listMinimumSize);
        chatPanel.setMinimumSize(chatMinimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(800, 600));

        panel.add(splitPane, BorderLayout.CENTER);

    }

    public void valueChanged(ListSelectionEvent e) {
        if (jRoomsList.getSelectedIndex() >= 0){
            getPresenter().selectRoom(jRoomsList.getSelectedValue());
            getFrame().setTitle(jRoomsList.getSelectedValue().getName()+" - SwaggaIRC");
        }
    }

    private void deleteRoomConfirmation(Room room){
        int dialog = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete "+room.getName()+"?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if(dialog == 0){
            getPresenter().removeRoom(room);
        }
    }

    @Override
    public void setRoomsList(List<Room> roomsList){
        jRoomsList.setListData(roomsList.toArray(new Room[roomsList.size()]));
    }

    @Override
    public void setChatView(View view) {
        //TODO
        chatPanel.removeAll();
        chatPanel.add(view.getPanel());
    }

    @Override
    public void setTitle(String title) {
        //TODO
        getFrame().setTitle(title);
    }

}
