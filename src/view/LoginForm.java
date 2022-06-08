package view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.*;
import java.util.Locale;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel loginPanel;
    public Admin admin;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(900, 700));
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                //cette fct nous permettra de checker si ces paramettres sont vrais ou non!
                admin = getAuthentificateUser(login, password);

                if (admin != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Login or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        setVisible(true);
        btnOK.addMouseListener(new MouseAdapter() {
        });
    }

    private Admin getAuthentificateUser(String login, String password) {
        Admin admin = null;

        final String DB_URL = "jdbc:mysql://localhost/Mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM admin WHERE login=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin(login, password);
                admin.setLogin(login);
                admin.setPassword(password);

            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        Admin admin = loginForm.admin;

        if (admin != null) {
            System.out.println("Successful Authentification of: " + admin.getLogin());
        } else {
            System.out.println("Authentification canceled");
        }
    }


}
