package dao;

import java.sql.*;

public class DBinteraction {
    static String DB_URL = "jdbc:mysql://localhost/mediatheque?serverTimezone=UTC";
    static String USERNAME = "root";
    static String PASSWORD = "";
    static Connection con;
    static Statement st;

    public static void Connect(){
        try {
            con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static int Maj(String sql)
    {
        int nb = 0;
        try {
            nb = st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nb;
    }


    public static ResultSet select(String sql)
    {
        ResultSet res = null;
        try {
            res = st.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }


    public static void disconnect()
    {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
