package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorArrow extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus,int row,int col){
        super.getTableCellRendererComponent(table,value,Selected,hasFocus,row,col);

        setBackground(Color.cyan);

        return this;
    }
}
