/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */
import model.Produit;
import service.ProduitService;

import java.util.List;

public class ProduitController {

    private ProduitService service = new ProduitService();

    public void ajouterProduit(Produit p) throws Exception {
        service.ajouterProduit(p);
    }

    public void modifierProduit(Produit p) throws Exception {
        service.modifierProduit(p);
    }

    public void supprimerProduit(int id) {
        service.supprimerProduit(id);
    }

    public List<Produit> listerProduits() {
        return service.listerProduits();
    }
}