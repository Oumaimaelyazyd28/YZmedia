/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Administrateur
 */
public class ColorTemp extends JTable{
    @Override
    public  Component prepareRenderer(TableCellRenderer renderer,int rowIndex, int columnIndex){
        Component component=super.prepareRenderer(renderer, rowIndex, columnIndex)
                ;
        int i = (int) getValueAt(rowIndex, columnIndex);
        if(i>=30) {
            component.setBackground(Color.red);
        } else if(i<30) {
            component.setBackground(Color.green);
        }
        return component;
    }
}