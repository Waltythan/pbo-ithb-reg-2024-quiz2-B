package View;

import Controller.UserController;
import Model.Users;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserController userController;

    public LoginView() {
        userController = new UserController();

        setTitle("Museum Ambazinggg - Login sek mas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> performLogin());
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        add(new JLabel("Museum Ambazinggg"), BorderLayout.NORTH);
    }

    private void performLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        Users user = userController.login(email, password);
        if (user != null) {
            new ArtworkListView(user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Login gagal. Periksa email atau password Anda.", 
                "Login Gagal", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}