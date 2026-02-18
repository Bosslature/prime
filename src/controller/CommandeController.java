/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */
import model.Commande;
import model.LigneCommande;
import service.CommandeService;

import java.util.List;

public class CommandeController {

    private CommandeService service = new CommandeService();

    public void creerCommande(Commande c) throws Exception {
        service.creerCommande(c);
    }

    public void ajouterLigneCommande(Commande c, LigneCommande lc) throws Exception {
        service.ajouterLigneCommande(c, lc);
    }

    public List<Commande> listerCommandes() {
        return service.listerCommandes();
    }

    public List<LigneCommande> listerLignes(int commandeId) {
        return service.listerLignes(commandeId);
    }
}

