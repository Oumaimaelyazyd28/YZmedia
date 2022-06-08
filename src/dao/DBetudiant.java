package dao;

import model.Etudiant;
import model.Professeur;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class DBetudiant {
    public static Etudiant addEtudiantToDataBase(String nom, String prenom, String cin, String cne, String email, String code) {

        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        Etudiant etudiant = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO etudiant(nom,prenom,cin,cne,email,code_abonnement)" + "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, cin);
            preparedStatement.setString(4, cne);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, code);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                etudiant = new Etudiant(nom,prenom,cin,cne,email,code);
            }
            JOptionPane.showMessageDialog(null, "Etudiant bien ajouter !");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return etudiant;
    }

    public static void modifierEtudiant(JTable table,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4,JTextField tf5,JTextField tf6){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un etudiant !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE etudiant set nom=?,prenom=?,cin=?,cne=?,email=?,code_abonnement=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,tf3.getText().toString());
                preparedStatement.setString(4,tf4.getText().toString());
                preparedStatement.setString(5,tf5.getText().toString());
                preparedStatement.setString(6,tf6.getText().toString());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussite !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void supprimerEtudiant(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un etudiant !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM etudiant where id='"+id+"'";
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
        String sql = "SELECT * FROM etudiant";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void chercherEtudiant(JTable table,JTextField tf){
        DBinteraction.Connect();
        String tx = tf.getText().toString();
        String sql1 = "SELECT * FROM etudiant WHERE nom='"+tx+"' OR prenom='"+tx+"' OR cin='"+tx+"' OR cne='"+tx+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }

    public static String genererCode(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"+"0123456789";
        StringBuilder s = new StringBuilder(5);

        for (int i=0; i<5; i++){
            int index = (int)(str.length()*Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }
}




















