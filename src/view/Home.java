package view;

import model.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame{
    private JPanel homePanel;
    private JButton professeurButton;
    private JButton kindelsButton;
    private JButton etudiantButton;
    private JButton documentButton;
    private JButton emprunteButton;
    private JButton controleButton;
    private JButton seDeconecteButton;

    public Home() {
        setTitle("Home");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(900, 700));
        //setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


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
                LoginForm loginForm = new LoginForm(null);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
    }
}
