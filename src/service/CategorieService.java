/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.CategorieDAO;
import model.Categorie;

import java.util.List;

public class CategorieService {

    private CategorieDAO dao = new CategorieDAO();

    public void ajouterCategorie(Categorie c) throws Exception {
        if (c.getLibelle() == null || c.getLibelle().trim().isEmpty()) {
            throw new Exception("Le libellé de catégorie est obligatoire.");
        }
        // Vérifier unicité
        for (Categorie exist : dao.findAll()) {
            if (exist.getLibelle().equalsIgnoreCase(c.getLibelle().trim())) {
                throw new Exception("Cette catégorie existe déjà !");
            }
        }
        dao.save(c);
    }

    public void modifierCategorie(Categorie c) throws Exception {
        if (c.getLibelle() == null || c.getLibelle().trim().isEmpty()) {
            throw new Exception("Le libellé de catégorie est obligatoire.");
        }
        dao.update(c);
    }

    public void supprimerCategorie(int id) {
        dao.delete(id);
    }

    public List<Categorie> listerCategories() {
        return dao.findAll();
    }
}

