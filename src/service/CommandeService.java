/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.CommandeDAO;
import DAO.LigneCommandeDAO;
import model.Commande;
import model.LigneCommande;
import model.Produit;

import java.util.List;

public class CommandeService {

    private CommandeDAO commandeDAO = new CommandeDAO();
    private LigneCommandeDAO ligneDAO = new LigneCommandeDAO();

    public void creerCommande(Commande c) throws Exception {
        if (c.getUtilisateur() == null) throw new Exception("Utilisateur requis.");
        commandeDAO.save(c);
    }

    public void ajouterLigneCommande(Commande c, LigneCommande lc) throws Exception {
        Produit p = lc.getProduit();
        if (lc.getQuantite() <= 0) throw new Exception("Quantité > 0 requise.");
        if (p.getStockActuel() < lc.getQuantite()) throw new Exception("Stock insuffisant.");
        lc.setPrixUnitaire(p.getPrixVente());
        ligneDAO.save(lc, c.getId());
    }

    public List<Commande> listerCommandes() {
        return commandeDAO.findAll();
    }

    public List<LigneCommande> listerLignes(int commandeId) {
        return ligneDAO.findByCommande(commandeId);
    }
}