package com.flight.view;

import javax.swing.*;
import java.sql.*;
import com.flight.dao.DBConnection;

public class LoginPage extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JComboBox<String> roleBox;
    private JButton btnLogin;

    public LoginPage() {
        // Change the title here
        setTitle("SkyBooker - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setBounds(50, 30, 100, 30);
        add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Admin", "User"});
        roleBox.setBounds(150, 30, 180, 30);
        add(roleBox);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 30);
        add(userLabel);

        txtUser = new JTextField();
        txtUser.setBounds(150, 70, 180, 30);
        add(txtUser);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 110, 100, 30);
        add(passLabel);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 110, 180, 30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 160, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(e -> checkLogin());
    }

    private void checkLogin() {
        String role = roleBox.getSelectedItem().toString();
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        try (Connection con = DBConnection.getCon()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, role + " Login Successful!");
                dispose();
                if (role.equalsIgnoreCase("Admin")) {
                    new AdminDashboard(username).setVisible(true);
                } else {
                    new UserDashboard(username).setVisible(true); // pass username
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username/Password/Role!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while login!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
