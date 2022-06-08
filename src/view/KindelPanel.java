package view;
import dao.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KindelPanel extends JFrame{
    private JPanel kindelPanel;
    private JButton button1;
    private JTextField tfKindel;
    private JTextField tfMac;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JTextField tfChercher;
    private JButton rechercherButton;
    private JButton refreshButton;
    private JTable table1;
    private JComboBox cbModel;
    private JComboBox cbVersion;

    public KindelPanel() {
        setContentPane(kindelPanel);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        DBkindel.tableLoad(table1);
        setVisible(true);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterKindel();
                DBkindel.tableLoad(table1);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBkindel.modifierKindel(table1,tfKindel,tfMac,cbModel,cbVersion);
                DBkindel.tableLoad(table1);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBkindel.supprimerKindel(table1);
                DBkindel.tableLoad(table1);
            }
        });
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBkindel.chercherKindel(table1,tfChercher);
            }
        });

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBkindel.tableLoad(table1);
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
                String kindel = table1.getModel().getValueAt(ligne, 1).toString();
                String mac = table1.getModel().getValueAt(ligne, 2).toString();
                String model = table1.getModel().getValueAt(ligne, 3).toString();
                String version = table1.getModel().getValueAt(ligne, 4).toString();
                tfKindel.setText(kindel);
                tfMac.setText(mac);
                cbModel.setSelectedItem(model);
                cbVersion.setSelectedItem(version);
            }
        });
    }

    public void ajouterKindel() {
        String kindel = tfKindel.getText();
        String mac = tfMac.getText();
        String model = cbModel.getSelectedItem().toString();
        String version = cbVersion.getSelectedItem().toString();

        if (kindel.isEmpty() || mac.isEmpty() || model.isEmpty() || version.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Merci de remplir tous les champs",
                    "Réssayer",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Kindel kindel1 = DBkindel.addKindelToDataBase(kindel,mac,model,version);
        if (kindel1 == null) {
            JOptionPane.showMessageDialog(this,
                    "Echec",
                    "Essayer à nouveau",
                    JOptionPane.ERROR_MESSAGE);
        }


    }


    public static void main(String[] args) {
        KindelPanel kindelPanel = new KindelPanel();
    }
}
