package view;

import dao.DBcontrole;
import view.changeTable.changeTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class Controle extends JFrame{
    private JTable table1;
    private JTable table2;
    private JButton activerButton;
    private JPanel conrolPanel;
    private JButton activer2Button;
    private JButton button1;
    ColorArrow colorArrow = new ColorArrow();
    ColorTemp colorTemp = new ColorTemp();

    public Controle() {
        setContentPane(conrolPanel);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        //table1.setBackground(Color.GREEN);

        setVisible(true);
        DBcontrole.importData(table2,"src/view/files/temperature.txt");
        //DBcontrole.changeTable(table2,1);
        createTable();
        DBcontrole.importNoiseData(table1,"src/view/files/bruit.txt");

        //table2.setDefaultRenderer(table2.getColumnClass(0),colorArrow);

        activerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                activerButton.setText("Désactiver");
            }
        });
        activer2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                activer2Button.setText("Désactiver");
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

        table2.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);

            }
        });
    }

    public void createTable(){
        table2.setModel(new changeTable());
    }

    public void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int st_val = Integer.parseInt(table.getValueAt(row, 0).toString());
                int req_val = 2000;
                if (st_val < req_val) {
                    c.setBackground(Color.MAGENTA);
                } else {
                    c.setBackground(Color.GREEN);
                }
                return c;
            }
        });
    }


    public static void main(String[] args) {
        Controle controle = new Controle();
    }

}
