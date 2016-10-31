package client.login.impl;

import client.base.BaseView;
import client.login.LoginPresenter;
import client.login.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginViewImpl extends BaseView<LoginPresenter> implements LoginView {

    JPasswordField password;
    JTextField user;
    JPanel panel;
    JFrame LoginFrame;

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
        this.LoginFrame =  new JFrame(title);

        // Set graphics settings, like size and position.
        LoginFrame.setSize(300,150);
        LoginFrame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        LoginFrame.setResizable(false);
        LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create a Panel to show on our frame.
        this.panel = new JPanel();
        LoginFrame.add(panel);
        setComponents(panel);

        // Show the frame.
        LoginFrame.setVisible(true);
    }

    private static boolean disableButtons(JTextField user, JPasswordField pass) {
        return (user.getText().equals("") || pass.getPassword().length == 0);
    }

    private void setComponents(JPanel panel){

        panel.setLayout(null);

        // User elements.
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(15, 15, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(95, 15, 190, 25);
        panel.add(userText);

        // Password elements.
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(15, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(95, 50, 190, 25);
        passwordText.setToolTipText("La contraseña debe de tener un minimo de 6 caracteres.");
        panel.add(passwordText);

        passwordText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                checkComponents(userText,passwordText);
            }
        });

        // Button elements.
        JButton loginButton = new JButton (new AbstractAction("Login") {
            public void actionPerformed(ActionEvent e) {
                checkComponents(userText,passwordText);
            }
        });


        loginButton.setBounds(190, 90, 100, 30);
        panel.add(loginButton);

        JButton registerButton = new JButton (new AbstractAction("Registrar") {
            public void actionPerformed(ActionEvent e) {
                checkComponents(userText,passwordText);
            }
        });

        registerButton.setBounds(8, 90, 100, 30);
        panel.add(registerButton);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        userText.getDocument().addDocumentListener(new DocumentListener() {
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
                if (disableButtons(userText, passwordText)){
                    loginButton.setEnabled(false);
                    registerButton.setEnabled(false);
                }
                else {
                    loginButton.setEnabled(true);
                    registerButton.setEnabled(true);
                }

            }


        });
        passwordText.getDocument().addDocumentListener(new DocumentListener() {
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
                if (disableButtons(userText, passwordText)){
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

    private void checkComponents(JTextField userField, JPasswordField passwordField){
        this.user = userField;
        this.password = passwordField;
        getPresenter().onLogin();

    }

    public void showError(String message, String title){
        JOptionPane.showMessageDialog(panel, message, title, JOptionPane.WARNING_MESSAGE);

    }

    public void close(){
        this.close();
    }


}
