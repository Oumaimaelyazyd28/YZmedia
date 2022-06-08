package dao;

import model.*;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.plaf.multi.MultiToolTipUI;
import java.sql.*;

public class DBemprunt {
    public static Emprunt addEmpruntToDataBase(String user, String cin, String name, String kindel, String rendre) {

        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

         Emprunt emprunt = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO emprunt(utilisateur,cin,nom,kindel,rendre)" + "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, cin);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, kindel);
            preparedStatement.setString(5, rendre);


            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                emprunt = new Emprunt(user,cin,name,kindel,rendre);
            }
            JOptionPane.showMessageDialog(null, "Une Kindel a été emprunter !");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emprunt;
    }

    public static void modifierEmprunt(JTable table,JComboBox cb1,JTextField tf1,JTextField tf2,JComboBox cb2,JComboBox cb3){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une ligne !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE emprunt set utilisateur=?,cin=?,nom=?,kindel=?,rendre=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,cb1.getSelectedItem().toString());
                preparedStatement.setString(2,tf1.getText().toString());
                preparedStatement.setString(3,tf2.getText().toString());
                preparedStatement.setString(4,cb2.getSelectedItem().toString());
                preparedStatement.setString(5,cb3.getSelectedItem().toString());

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussite !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void supprimerEmprunt(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez une ligne !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM emprunt where id='"+id+"'";
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
        String sql = "SELECT * FROM emprunt";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void chercherEmprunt(JTable table,JTextField tf){
        DBinteraction.Connect();
        String tx = tf.getText().toString();
        String sql1 = "SELECT * FROM emprunt WHERE cin='"+tx+"' OR kindel='"+tx+"' OR nom='"+tx+"' OR rendre='"+tx+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

    public static void remplirNom(JComboBox cb,JTextField tf1,JTextField tf2){
        DBinteraction.Connect();
        String mm = null;
        String txt = tf1.getText();
        if(cb.getSelectedItem().toString().equals("Etudiant")){
        String sql1 = "SELECT nom,prenom FROM etudiant WHERE cin='"+txt+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                mm = resultSet.getString("nom")+" "+resultSet.getString("prenom");
            } catch (SQLException e) {
                e.printStackTrace();
            }}

        System.out.println("etudiant");
        }
        else {
            String sql1 = "SELECT nom,prenom FROM profs WHERE cin='"+txt+"'";
            ResultSet resultSet = DBinteraction.select(sql1);
            while (true){
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    mm = resultSet.getString("nom")+" "+resultSet.getString("prenom");
                } catch (SQLException e) {
                    e.printStackTrace();
                }}

            System.out.println("prof");
        }
        tf2.setText(mm);
    }

    public static void updateCombo(JComboBox comboBox1){
        DBinteraction.Connect();
        String sql = "SELECT kindel FROM kindel";
        //PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = DBinteraction.select(sql);

        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                comboBox1.addItem(resultSet.getString("kindel"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
