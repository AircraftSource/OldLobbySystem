package de.aircraft.lobbysystem.mysql.onlinetime;

import java.sql.*;

public class OnlineMySQL {

    private String HOST = "localhost";
    private String PORT = "3306";
    private String DATABASE = "OnlineTime";
    private String USER = "administrator";
    private String PASSWORD = "ad66d65bd2cb0df93366c45dac948444";
    private Connection con;

    public OnlineMySQL() {
        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":"+PORT+"/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        } catch (SQLException e) {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if(con != null) {
                con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}
