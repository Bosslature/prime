/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADMIN
 */
import model.Produit;
import model.Categorie;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    public void save(Produit p) {
        String sql = "INSERT INTO produit(nom, prix_vente, stock_actuel, seuil_alerte, categorie_id) VALUES(?,?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrixVente());
            ps.setInt(3, p.getStockActuel());
            ps.setInt(4, p.getSeuilAlerte());
            ps.setInt(5, p.getCategorie().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Produit p) {
        String sql = "UPDATE produit SET nom=?, prix_vente=?, stock_actuel=?, seuil_alerte=?, categorie_id=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrixVente());
            ps.setInt(3, p.getStockActuel());
            ps.setInt(4, p.getSeuilAlerte());
            ps.setInt(5, p.getCategorie().getId());
            ps.setInt(6, p.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM produit WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produit> findAll() {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT p.*, c.id AS cat_id, c.libelle FROM produit p " +
                     "JOIN categorie c ON p.categorie_id = c.id";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("cat_id"));
                c.setLibelle(rs.getString("libelle"));

                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrixVente(rs.getDouble("prix_vente"));
                p.setStockActuel(rs.getInt("stock_actuel"));
                p.setSeuilAlerte(rs.getInt("seuil_alerte"));
                p.setCategorie(c);

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}