package dao;

import net.proteanit.sql.DbUtils;
import model.*;

import javax.swing.*;
import java.sql.*;

public class DBdoucument {
    public static Document addDocumentToDataBase(String titre, String auteur, String nbrPage, String type, String isbn, String editeur, String url, String anneeEdition) {

        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        Document document1 = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO livres(titre,auteur,nbr_page,type,isbn,editeur,url,annee_edition)" + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, nbrPage);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, isbn);
            preparedStatement.setString(6, editeur);
            preparedStatement.setString(7, url);
            preparedStatement.setString(8, anneeEdition);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                document1 = new Document(titre, auteur, nbrPage, type, isbn, editeur, url, anneeEdition);
                document1.setTitre(titre);
                document1.setAuteur(auteur);
                document1.setNbrPage(nbrPage);
                document1.setType(type);
                document1.setIsbn(isbn);
                document1.setEditeur(editeur);
                document1.setUrl(url);
                document1.setAnneeEdition(anneeEdition);
            }
            JOptionPane.showMessageDialog(null, "Document bien ajouter !");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return document1;
    }

    public static void modifierDocument(JTable table,JTextField tf1,JTextField tf2,JTextField tf3,JComboBox jc,JTextField tf4,JTextField tf5,JTextField tf6,JTextField tf7){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if (ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un document !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE livres set titre=?,auteur=?,nbr_page=?,type=?,isbn=?,editeur=?,url=?,annee_edition=? where id='"+id+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,tf1.getText().toString());
                preparedStatement.setString(2,tf2.getText().toString());
                preparedStatement.setString(3,tf3.getText().toString());
                preparedStatement.setString(4,jc.getSelectedItem().toString());
                preparedStatement.setString(5,tf4.getText().toString());
                preparedStatement.setString(6,tf5.getText().toString());
                preparedStatement.setString(7,tf6.getText().toString());
                preparedStatement.setString(8,tf7.getText().toString());

                //preparedStatement.setString(8, String.valueOf(table1.getSelectedRow()));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Modification réussite !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void supprimerDocument(JTable table){
        final String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int ligne = table.getSelectedRow();
        if(ligne == -1){
            JOptionPane.showMessageDialog(null,"Séléctionnez un Document !");
        }
        else {
            String id = table.getModel().getValueAt(ligne,0).toString();
            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM livres where id='"+id+"'";
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
        String sql = "SELECT * FROM livres";
        ResultSet resultSet = DBinteraction.select(sql);

        table1.setModel(DbUtils.resultSetToTableModel(resultSet));
    }


    public static void chercherAbonnement(JTable table,JTextField tf){
        DBinteraction.Connect();
        String isbn = tf.getText().toString();
        String sql1 = "SELECT * FROM livres WHERE isbn='"+isbn+"' OR auteur='"+isbn+"' OR editeur='"+isbn+"' OR titre='"+isbn+"'";
        ResultSet resultSet = DBinteraction.select(sql1);
        table.setModel(DbUtils.resultSetToTableModel(resultSet));
    }
}
