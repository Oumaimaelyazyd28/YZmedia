package view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

import dao.*;
import model.*;

public class LivrePanel extends JFrame {
    private JPanel livrePanel;
    private JButton ajouterButton;
    private JButton rechercherButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JTextField tfChercher;
    private JTextField tfIsbn;
    private JTextField tfUrl;
    private JTextField tfEditeur;
    private JTextField tfEdition;
    private JTextField tfTitre;
    private JTextField tfAuteur;
    private JTextField tfPages;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton refreshButton;
    private JButton button1;

    public LivrePanel() {
        setTitle("livre");
        setContentPane(livrePanel);
        setMinimumSize(new Dimension(700, 500));
        //setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        DBdoucument.tableLoad(table1);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterLivre();
                DBdoucument.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBdoucument.modifierDocument(table1, tfTitre, tfAuteur, tfPages, comboBox1, tfIsbn, tfEditeur, tfUrl, tfEdition);
                DBdoucument.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBdoucument.supprimerDocument(table1);
                DBdoucument.tableLoad(table1);
            }
        });
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBdoucument.chercherAbonnement(table1, tfChercher);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int ligne = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(null,"you have chose this bock !"+ligne);
                String titre = table1.getModel().getValueAt(ligne, 1).toString();
                String auteur = table1.getModel().getValueAt(ligne, 2).toString();
                String nbrPage = table1.getModel().getValueAt(ligne, 3).toString();
                String type = table1.getModel().getValueAt(ligne, 4).toString();
                String isbn = table1.getModel().getValueAt(ligne, 5).toString();
                String editeur = table1.getModel().getValueAt(ligne, 6).toString();
                String url = table1.getModel().getValueAt(ligne, 7).toString();
                String anneeEdition = table1.getModel().getValueAt(ligne, 8).toString();

                tfTitre.setText(titre);
                tfAuteur.setText(auteur);
                tfPages.setText(nbrPage);
                comboBox1.setSelectedItem(type);
                tfIsbn.setText(isbn);
                tfEditeur.setText(editeur);
                tfUrl.setText(url);
                tfEdition.setText(anneeEdition);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBdoucument.tableLoad(table1);
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

    public void ajouterLivre() {
        String titre = tfTitre.getText();
        String auteur = tfAuteur.getText();
        String nbrPage = tfPages.getText();
        String type = comboBox1.getSelectedItem().toString();
        String isbn = tfIsbn.getText();
        String editeur = tfEditeur.getText();
        String url = tfUrl.getText();
        String anneeEdition = tfEdition.getText();

        if (titre.isEmpty() || auteur.isEmpty() || nbrPage.isEmpty() || type.isEmpty() || isbn.isEmpty() || editeur.isEmpty() || url.isEmpty() || anneeEdition.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Document document = DBdoucument.addDocumentToDataBase(titre, auteur, nbrPage, type, isbn, editeur, url, anneeEdition);
        if (document == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        LivrePanel livrePanel = new LivrePanel();
    }

}
