/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.LigneCommandeDAO;
import java.sql.Date;
import model.LigneCommande;

import java.util.List;
import model.Produit;

public class StatistiquesService {

    private LigneCommandeDAO ligneDAO = new LigneCommandeDAO();

    public double chiffreAffairesParCommande(int commandeId) {
        double total = 0;
        List<LigneCommande> lignes = ligneDAO.findByCommande(commandeId);
        for (LigneCommande lc : lignes) total += lc.getMontant();
        return total;
    }

    public List<Produit> topProduitsVendues(Date debut, Date fin, int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double chiffreAffairesPeriode(Date debut, Date fin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double chiffreAffairesParJour(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Produit> produitsEnRupture() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
