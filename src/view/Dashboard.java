package view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Dashboard extends JFrame {
    private JPanel dashboardPanel;
    private JButton professeurButton;
    private JButton kindelsButton;
    private JButton etudiantButton;
    private JButton documentButton;
    private JButton emprunteButton;
    private JButton controleButton;
    private JButton seDeconecteButton;

    public Dashboard() {
        setTitle("Home");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(900, 700));
        //setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        LoginForm loginForm = new LoginForm(this);
        Admin admin = loginForm.admin;
        if (admin != null) {
            //lbAdmin.setText("Admin : "+admin.getLogin());
            setLocationRelativeTo(null);
            setVisible(true);
        } else {
            dispose();
        }
        professeurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfPanel profPanel = new ProfPanel();
                dispose();
            }
        });
        etudiantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EtudiantPanel etudiantPanel = new EtudiantPanel();
                dispose();
            }
        });
        kindelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KindelPanel kindelPanel = new KindelPanel();
                dispose();
            }
        });
        documentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivrePanel livrePanel = new LivrePanel();
                dispose();
            }
        });
        emprunteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmpruntPanel empruntPanel = new EmpruntPanel();
                dispose();
            }
        });
        controleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controle controle = new Controle();
                dispose();
            }
        });
        seDeconecteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm1 = new LoginForm(null);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
    }

}
