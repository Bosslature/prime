/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */
import model.Utilisateur;
import service.UtilisateurService;

import java.util.List;

public class UtilisateurController {

    private UtilisateurService service = new UtilisateurService();

    public void ajouterUtilisateur(Utilisateur u) throws Exception {
        service.ajouterUtilisateur(u);
    }

    public void modifierUtilisateur(Utilisateur u) throws Exception {
        service.modifierUtilisateur(u);
    }

    public void supprimerUtilisateur(int id) {
        service.supprimerUtilisateur(id);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return service.listerUtilisateurs();
    }
}