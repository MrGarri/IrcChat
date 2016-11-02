package client.login.impl;

import client.base.BaseView;
import client.login.LoginPresenter;
import client.login.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public class LoginViewImpl extends BaseView<LoginPresenter> implements LoginView {

    JPasswordField password;
    JTextField user;
    JPanel panel;
    JFrame loginFrame;

    @Override
    public void initialize() {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI("Login - SwaggaIRC"));
    }

    @Override
    public String getUser() {
        return user.getText();
    }

    @Override
    public String getPassword() {
        return password.getPassword().toString();
    }

    private void createAndShowGUI(String title){
        this.loginFrame =  new JFrame(title);

        // Set graphics settings, like size and position.
        loginFrame.setSize(300,150);
        loginFrame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create a Panel to show on our frame.
        this.panel = new JPanel();
        loginFrame.add(panel);
        setComponents(panel);

        // Show the frame.
        loginFrame.setVisible(true);
    }

    private boolean disableButtons(JTextField user, JPasswordField pass) {
        return (user.getText().equals("") || pass.getPassword().length == 0);
    }

    private void setComponents(JPanel panel){

        panel.setLayout(null);

        // User elements.
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(15, 15, 80, 25);
        panel.add(userLabel);

        user = new JTextField(20);
        user.setBounds(95, 15, 190, 25);
        panel.add(user);

        // Password elements.
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(15, 50, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(95, 50, 190, 25);
        password.setToolTipText("La contraseña debe de tener un minimo de 6 caracteres.");
        panel.add(password);

        // Button elements.
        JButton loginButton = new JButton (new AbstractAction("Login") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onLogin();
            }
        });


        loginButton.setBounds(190, 90, 100, 30);
        panel.add(loginButton);

        JButton registerButton = new JButton (new AbstractAction("Registrar") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onRegister();
            }
        });



        registerButton.setBounds(8, 90, 100, 30);
        panel.add(registerButton);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (disableButtons(user, password)){
                    loginButton.setEnabled(false);
                    registerButton.setEnabled(false);
                }
                else {
                    loginButton.setEnabled(true);
                    registerButton.setEnabled(true);
                }

            }


        });
        password.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (disableButtons(user, password)){
                    loginButton.setEnabled(false);
                    registerButton.setEnabled(false);
                }
                else {
                    loginButton.setEnabled(true);
                    registerButton.setEnabled(true);
                }

            }


        });

    }

    @Override
    public void showError(String message){
        JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void close(){
        loginFrame.dispose();
    }


}
