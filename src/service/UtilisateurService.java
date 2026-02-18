/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.UtilisateurDAO;
import model.Utilisateur;

import java.util.List;

public class UtilisateurService {

    private UtilisateurDAO dao = new UtilisateurDAO();

    public void ajouterUtilisateur(Utilisateur u) throws Exception {
        if (u.getLogin() == null || u.getLogin().trim().isEmpty()) {
            throw new Exception("Login obligatoire.");
        }
        if (dao.loginExiste(u.getLogin())) {
            throw new Exception("Login déjà utilisé.");
        }
        dao.save(u);
    }

    public void modifierUtilisateur(Utilisateur u) throws Exception {
        if (u.getLogin() == null || u.getLogin().trim().isEmpty()) {
            throw new Exception("Login obligatoire.");
        }
        dao.update(u);
    }

    public void supprimerUtilisateur(int id) {
        dao.delete(id);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return dao.findAll();
    }
}