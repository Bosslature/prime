/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


/**
 *
 * @author ADMIN
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3307/GestionRestaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            // Affiche l'erreur si la connexion échoue
            JOptionPane.showMessageDialog(null, "Erreur de connexion : " + ex.getMessage());
        }
        return connect;
    }
}
