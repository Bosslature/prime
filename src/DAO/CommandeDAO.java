/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Commande;
import model.Utilisateur;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CommandeDAO {
    
    public void save(Commande c) {
        String sql = "INSERT INTO commande(date_commande, etat, total, utilisateur_id) VALUES(?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new Timestamp(c.getDateCommande().getTime()));
            ps.setString(2, c.getEtat().name());
            ps.setDouble(3, c.getTotal());
            ps.setInt(4, c.getUtilisateur().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) c.setId(rs.getInt(1));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement : " + e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void annuler(Commande c) {

    String sql = "UPDATE commande SET etat = ? WHERE id = ?";
    
    try (Connection cn = DBConnection.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, "annuler");   // valeur de etat
        ps.setInt(2, c.getId());      // id de la commande à modifier

        ps.executeUpdate();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Erreur lors de l'annulation : " + e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
    }
}

    

    public List<Commande> findAll() {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT c.*, u.id AS uid, u.login, u.role FROM commande c " +
                     "JOIN utilisateur u ON c.utilisateur_id = u.id";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("uid"));
                u.setLogin(rs.getString("login"));
                u.setRole(model.Role.valueOf(rs.getString("role")));

                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setDateCommande(rs.getTimestamp("date_commande"));
                c.setEtat(Commande.Etat.valueOf(rs.getString("etat")));
                c.setTotal(rs.getDouble("total"));
                c.setUtilisateur(u);
                list.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erreur de chargement de la liste de commande : " + e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
}
