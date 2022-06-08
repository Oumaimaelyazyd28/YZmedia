package dao;

import model.*;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class DBkindel {
    public static Kindel addKindelToDataBase(String kindel, String mac, String model, String version) {

        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        Kindel kindel1 = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO kindel(kindel,mac,model,version)" + "VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, kindel);
            preparedStatement.setString(2, mac);
            preparedStatement.setString(3, model);
            preparedStatement.setString(4, version);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                kindel1 = new Kindel(kindel,mac,model,version);
            }
            JOptionPane.showMessageDialog(null, "Kindel bien ajouter !");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return kindel1;
    }

    public static void modifierKindel(JTable table,JTextField tf1,JTextField tf2,JComboBox cb1, JComboBox cb2){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une Kindel !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE kindel set kindel=?,mac=?,model=?,version=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,cb1.getSelectedItem().toString());
                preparedStatement.setString(4,cb2.getSelectedItem().toString());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussite !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void supprimerKindel(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une kindel !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM kindel where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null,"Supprission réussite !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void tableLoad(JTable table1){
        DBinteraction.Connect();
        String sql = "SELECT * FROM kindel";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void chercherKindel(JTable table,JTextField tf){
        DBinteraction.Connect();
        String tx = tf.getText().toString();
        String sql1 = "SELECT * FROM kindel WHERE kindel='"+tx+"' OR mac='"+tx+"' OR model='"+tx+"' OR version='"+tx+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

}
