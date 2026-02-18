/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */
import model.Categorie;
import service.CategorieService;

import java.util.List;

public class CategorieController {

    private CategorieService service = new CategorieService();

    public void ajouterCategorie(Categorie c) throws Exception {
        service.ajouterCategorie(c);
    }

    public void modifierCategorie(Categorie c) throws Exception {
        service.modifierCategorie(c);
    }

    public void supprimerCategorie(int id) {
        service.supprimerCategorie(id);
    }

    public List<Categorie> listerCategories() {
        return service.listerCategories();
    }
}
