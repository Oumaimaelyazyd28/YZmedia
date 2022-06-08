package dao;

import dao.DBinteraction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class DBcontrole {

    private static String temp;
    private static String salle;

    public static void readData(JButton j){
        try(Scanner input = new Scanner(new File("src/view/files/temperature1.txt"))){
            while (input.hasNextLine()){
                temp = "";
                String line;

                line = input.nextLine();

                try(Scanner data = new Scanner(line)) {
                    while (!data.hasNextInt()){
                        temp += data.next()+" ";
                    }
                    temp = temp.trim();
                }
            }

            System.out.println(temp+"\t");
        }
        catch (IOException e){
            System.out.println("echec !");
        }
        saveData(j);
    }

    public static void saveData(JButton jb){
        final String DB_URL = "jdbc:mysql://localhost/sfc?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        salle = jb.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO temperature(temperature, salle)"+"VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,temp);
            preparedStatement.setString(2,salle);

            //Insert row into the table
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void tableLoad(JTable table1, String x){
        DBinteraction.Connect();
        String filePath = x;
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String columnsName = firstLine;
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setColumnIdentifiers(new String[]{columnsName});

            Object[] tableLines = br.lines().toArray();
            for (int i=0; i<tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String dataRow = line;
                model.addRow(new String[]{dataRow});
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void importData(JTable jt, String x){
        String filePath = x;
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String columnsName = firstLine;
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setColumnIdentifiers(new String[]{columnsName});

            Object[] tableLines = br.lines().toArray();
            for (int i=0; i<tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String dataRow = line;
                model.addRow(new String[]{dataRow});
                sleep(500);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void importNoiseData(JTable jt, String x){
        String filePath = x;
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String columnsName = firstLine;
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setColumnIdentifiers(new String[]{columnsName});

            Object[] tableLines = br.lines().toArray();
            for (int i=0; i<tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String dataRow = line;
                model.addRow(new String[]{dataRow});
                sleep(500);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

}
