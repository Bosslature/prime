/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ADMIN
 */
import model.Categorie;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    public void save(Categorie c) {
        String sql = "INSERT INTO categorie(libelle) VALUES(?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getLibelle());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Categorie c) {
        String sql = "UPDATE categorie SET libelle=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getLibelle());
            ps.setInt(2, c.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM categorie WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categorie> findAll() {
        List<Categorie> list = new ArrayList<>();
        String sql = "SELECT * FROM categorie";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
