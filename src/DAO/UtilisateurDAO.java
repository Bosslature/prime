/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import model.Utilisateur;
import model.Role;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class UtilisateurDAO {
    public void save(Utilisateur u) {
       String sql = "INSERT INTO utilisateur(login, mot_de_passe, role) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getLogin());
            ps.setString(2, u.getMotDePasse());
            ps.setString(3, u.getRole().name());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Utilisateur u) {
        String sql = "UPDATE utilisateur SET login=?, role=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getLogin());
            ps.setString(2, u.getRole().name());
            ps.setInt(3, u.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM utilisateur WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loginExiste(String login) {
        String sql = "SELECT id FROM utilisateur WHERE login=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, login);
            return ps.executeQuery().next();
        } catch (Exception e) {
            return false;
        }
    }

    public List<Utilisateur> findAll() {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";

        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setRole(Role.valueOf(rs.getString("role")));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
