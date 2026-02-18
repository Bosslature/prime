/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.ProduitDAO;
import model.Produit;

import java.util.List;

public class ProduitService {

    private ProduitDAO dao = new ProduitDAO();

    public void ajouterProduit(Produit p) throws Exception {
        if (p.getNom() == null || p.getNom().trim().isEmpty()) {
            throw new Exception("Le nom du produit est obligatoire.");
        }
        if (p.getPrixVente() <= 0) {
            throw new Exception("Le prix doit être supérieur à 0.");
        }
        if (p.getStockActuel() < 0) {
            throw new Exception("Le stock ne peut pas être négatif.");
        }
        if (p.getSeuilAlerte() < 0) {
            throw new Exception("Le seuil d'alerte ne peut pas être négatif.");
        }
        dao.save(p);
    }

    public void modifierProduit(Produit p) throws Exception {
        ajouterProduit(p); // réutiliser validations
        dao.update(p);
    }

    public void supprimerProduit(int id) {
        dao.delete(id);
    }

    public List<Produit> listerProduits() {
        return dao.findAll();
    }
}