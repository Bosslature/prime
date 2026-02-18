/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.LigneCommande;
import model.Produit;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class LigneCommandeDAO {
    public void save(LigneCommande lc, int commandeId) {
        String sql = "INSERT INTO ligne_commande(commande_id, produit_id, quantite, prix_unitaire) VALUES(?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, commandeId);
            ps.setInt(2, lc.getProduit().getId());
            ps.setInt(3, lc.getQuantite());
            ps.setDouble(4, lc.getPrixUnitaire());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LigneCommande> findByCommande(int commandeId) {
        List<LigneCommande> list = new ArrayList<>();
        String sql = "SELECT lc.*, p.id AS pid, p.nom FROM ligne_commande lc " +
                     "JOIN produit p ON lc.produit_id = p.id WHERE lc.commande_id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, commandeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("pid"));
                p.setNom(rs.getString("nom"));

                LigneCommande lc = new LigneCommande();
                lc.setId(rs.getInt("id"));
                lc.setProduit(p);
                lc.setQuantite(rs.getInt("quantite"));
                lc.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                list.add(lc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
