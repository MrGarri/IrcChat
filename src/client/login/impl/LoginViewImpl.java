package client.login.impl;

import client.base.impl.BaseFrameView;
import client.login.LoginPresenter;
import client.login.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public class LoginViewImpl extends BaseFrameView<LoginPresenter> implements LoginView {

    private JPasswordField password;
    private JTextField user;

    private JButton loginButton;
    private JButton registerButton;

    @Override
    public String getTitle() {
        return "Login - SwaggaIRC";
    }

    @Override
    public String getUser() {
        return user.getText();
    }

    @Override
    public String getPassword() {
        return new String(password.getPassword());
    }

    @Override
    protected void initializeFrame(JFrame frame){

        // Set graphics settings, like size and position.
        frame.setSize(300,150);
        frame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show the frame.
        frame.setVisible(true);
    }

    protected void initializePanel(JPanel panel){

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
        loginButton = new JButton (new AbstractAction("Login") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onLogin();
            }
        });


        loginButton.setBounds(190, 90, 100, 30);
        panel.add(loginButton);

        registerButton = new JButton (new AbstractAction("Registrar") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onRegister();
            }
        });

        registerButton.setBounds(8, 90, 100, 30);
        panel.add(registerButton);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                disableButtons();
            }
        };

        user.getDocument().addDocumentListener(listener);
        password.getDocument().addDocumentListener(listener);

    }

    private void disableButtons() {
        boolean disabled = user.getText().trim().isEmpty() || password.getPassword().toString().isEmpty();
        loginButton.setEnabled(!disabled);
        registerButton.setEnabled(!disabled);
    }




}
