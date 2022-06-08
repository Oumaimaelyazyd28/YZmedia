package view;

import dao.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpruntPanel extends JFrame{
    private JPanel empruntPanel;
    private JButton button1;
    private JTextField tfCin;
    private JTextField tfNom;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JTextField tfChercher;
    private JButton rechercherButton;
    private JButton refreshButton;
    private JTable table1;
    private JComboBox cbRendre;
    private JComboBox cbUser;
    private JComboBox cbKindel;

    public EmpruntPanel() {
        setContentPane(empruntPanel);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        DBemprunt.tableLoad(table1);
        DBemprunt.updateCombo(cbKindel);
        setVisible(true);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterEmprunt();
                DBemprunt.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBemprunt.modifierEmprunt(table1,cbUser,tfCin,tfNom,cbKindel,cbRendre);
                DBemprunt.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBemprunt.supprimerEmprunt(table1);
                DBemprunt.tableLoad(table1);
            }
        });
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBemprunt.chercherEmprunt(table1,tfChercher);
            }
        });
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBemprunt.tableLoad(table1);
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
                String user = table1.getModel().getValueAt(ligne, 1).toString();
                String cin = table1.getModel().getValueAt(ligne, 2).toString();
                String name = table1.getModel().getValueAt(ligne, 3).toString();
                String kindel = table1.getModel().getValueAt(ligne, 4).toString();
                String rendre = table1.getModel().getValueAt(ligne, 6).toString();
                cbUser.setSelectedItem(user);
                tfCin.setText(cin);
                tfNom.setText(name);
                cbKindel.setSelectedItem(kindel);
                cbRendre.setSelectedItem(rendre);
            }
        });
        tfCin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBemprunt.remplirNom(cbUser,tfCin,tfNom);
            }
        });
    }

    public void ajouterEmprunt() {
        String user = cbUser.getSelectedItem().toString();
        String cin = tfCin.getText();
        String name = tfNom.getText();
        String kindel = cbKindel.getSelectedItem().toString();
        String rendre = cbRendre.getSelectedItem().toString();

        if (user.isEmpty() || cin.isEmpty() || name.isEmpty() || kindel.isEmpty() || rendre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Emprunt emprunt = DBemprunt.addEmpruntToDataBase(user,cin,name,kindel,rendre);
        if (emprunt == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EmpruntPanel empruntPanel = new EmpruntPanel();
    }
}
