package view;
import dao.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfPanel extends JFrame {
    private JPanel profPanel;
    private JButton button1;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfCin;
    private JTextField tfCnss;
    private JTextField tfCode;
    private JTextField tfEmail;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JTextField tfChercher;
    private JButton rechercherButton;
    private JButton refreshButton;
    private JTable table1;
    private JButton genererCodeButton;

    public ProfPanel() {
        setContentPane(profPanel);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        DBprof.tableLoad(table1);
        setVisible(true);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterProf();
                DBprof.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBprof.modifierProf(table1,tfNom,tfPrenom,tfCin,tfCnss,tfEmail,tfCode);
                DBprof.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBprof.supprimerProf(table1);
                DBprof.tableLoad(table1);
            }
        });
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBprof.chercherProf(table1,tfChercher);
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
                String cnss = table1.getModel().getValueAt(ligne, 4).toString();
                String email = table1.getModel().getValueAt(ligne, 5).toString();
                String code = table1.getModel().getValueAt(ligne, 6).toString();

                tfNom.setText(nom);
                tfPrenom.setText(prenom);
                tfCin.setText(cin);
                tfCnss.setText(cnss);
                tfEmail.setText(email);
                tfCode.setText(code);

            }
        });
        genererCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code;
                code = DBprof.genererCode();
                tfCode.setText(code);
            }
        });
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBprof.tableLoad(table1);
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
    }

    public void ajouterProf() {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String cin = tfCin.getText();
        String cnss = tfCnss.getText();
        String email = tfEmail.getText();
        String code = tfCode.getText();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || cnss.isEmpty() || email.isEmpty() || code.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Professeur professeur= DBprof.addProfToDataBase(nom,prenom,cin,cnss,email,code);
        if (professeur == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ProfPanel profPanel = new ProfPanel();
    }

}
