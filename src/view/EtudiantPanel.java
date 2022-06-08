package view;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EtudiantPanel extends JFrame{
    private JPanel etudiantPanel;
    private JButton button1;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfCin;
    private JTextField tfCne;
    private JTextField tfCode;
    private JTextField tfEmail;
    private JButton genererCodeButton;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JTextField tfChercher;
    private JButton rechercherButton;
    private JButton refreshButton;
    private JTable table1;

    public EtudiantPanel() {
        setContentPane(etudiantPanel);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        DBetudiant.tableLoad(table1);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterEtudiant();
                DBetudiant.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBetudiant.modifierEtudiant(table1,tfNom,tfPrenom,tfCin,tfCne,tfEmail,tfCode);
                DBetudiant.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBetudiant.supprimerEtudiant(table1);
                DBetudiant.tableLoad(table1);
            }
        });
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBetudiant.chercherEtudiant(table1,tfChercher);
            }
        });
        genererCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = DBetudiant.genererCode();
                tfCode.setText(code);
            }
        });

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBetudiant.tableLoad(table1);
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Home home = new Home();
                dispose();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String nom = table1.getModel().getValueAt(ligne, 1).toString();
                String prenom = table1.getModel().getValueAt(ligne, 2).toString();
                String cin = table1.getModel().getValueAt(ligne, 3).toString();
                String cne = table1.getModel().getValueAt(ligne, 4).toString();
                String email = table1.getModel().getValueAt(ligne, 5).toString();
                String code = table1.getModel().getValueAt(ligne, 6).toString();

                tfNom.setText(nom);
                tfPrenom.setText(prenom);
                tfCin.setText(cin);
                tfCne.setText(cne);
                tfEmail.setText(email);
                tfCode.setText(code);
            }
        });
    }

    public void ajouterEtudiant() {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String cin = tfCin.getText();
        String cne = tfCne.getText();
        String email = tfEmail.getText();
        String code = tfCode.getText();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || cne.isEmpty() || email.isEmpty() || code.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Etudiant etudiant = DBetudiant.addEtudiantToDataBase(nom,prenom,cin,cne,email,code);
        if (etudiant == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void main(String[] args) {
        EtudiantPanel etudiantPanel = new EtudiantPanel();
    }

}
