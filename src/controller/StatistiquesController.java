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
import service.StatistiquesService;

import java.sql.Date;
import java.util.List;

public class StatistiquesController {

    private StatistiquesService service = new StatistiquesService();

    public double chiffreAffairesParJour(Date date) {
        return service.chiffreAffairesParJour(date);
    }

    public double chiffreAffairesPeriode(Date debut, Date fin) {
        return service.chiffreAffairesPeriode(debut, fin);
    }

    public List<Produit> topProduitsVendues(Date debut, Date fin, int limit) {
        return service.topProduitsVendues(debut, fin, limit);
    }

    public List<Produit> produitsEnRupture() {
        return service.produitsEnRupture();
    }
}