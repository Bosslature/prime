/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADMIN
 */
import model.LigneCommande;
import model.Produit;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesDAO {

    // Chiffre d'affaires pour une date
    public double chiffreAffairesParJour(Date date) {
        double total = 0;
        String sql = "SELECT SUM(quantite * prix_unitaire) AS ca " +
                     "FROM ligne_commande lc " +
                     "JOIN commande c ON lc.commande_id = c.id " +
                     "WHERE DATE(c.date_commande) = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble("ca");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Chiffre d'affaires sur une période
    public double chiffreAffairesPeriode(Date debut, Date fin) {
        double total = 0;
        String sql = "SELECT SUM(quantite * prix_unitaire) AS ca " +
                     "FROM ligne_commande lc " +
                     "JOIN commande c ON lc.commande_id = c.id " +
                     "WHERE DATE(c.date_commande) BETWEEN ? AND ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, debut);
            ps.setDate(2, fin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble("ca");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Top produits vendus (en quantité) sur une période
    public List<Produit> topProduitsVendues(Date debut, Date fin, int limit) {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT p.id, p.nom, SUM(lc.quantite) AS totalVendu " +
                     "FROM ligne_commande lc " +
                     "JOIN produit p ON lc.produit_id = p.id " +
                     "JOIN commande c ON lc.commande_id = c.id " +
                     "WHERE DATE(c.date_commande) BETWEEN ? AND ? " +
                     "GROUP BY p.id, p.nom " +
                     "ORDER BY totalVendu DESC " +
                     "LIMIT ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, debut);
            ps.setDate(2, fin);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Produits en rupture ou en dessous du seuil
    public List<Produit> produitsEnRupture() {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE stock_actuel <= seuil_alerte";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setStockActuel(rs.getInt("stock_actuel"));
                p.setSeuilAlerte(rs.getInt("seuil_alerte"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

