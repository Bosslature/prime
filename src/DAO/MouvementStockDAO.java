/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.MouvementStock;
import model.Produit;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class MouvementStockDAO {
     public void save(MouvementStock m) {
        String sql = "INSERT INTO mouvement_stock(produit_id, type, quantite, motif) VALUES(?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, m.getProduit().getId());
            ps.setString(2, m.getType().name());
            ps.setInt(3, m.getQuantite());
            ps.setString(4, m.getMotif());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MouvementStock> findAll() {
        List<MouvementStock> list = new ArrayList<>();
        String sql = "SELECT ms.*, p.id AS pid, p.nom FROM mouvement_stock ms " +
                     "JOIN produit p ON ms.produit_id = p.id";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("pid"));
                p.setNom(rs.getString("nom"));

                MouvementStock ms = new MouvementStock();
                ms.setId(rs.getInt("id"));
                ms.setProduit(p);
                ms.setType(MouvementStock.Type.valueOf(rs.getString("type")));
                ms.setQuantite(rs.getInt("quantite"));
                ms.setMotif(rs.getString("motif"));
                ms.setDateMouvement(rs.getTimestamp("date_mouvement"));
                list.add(ms);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
