/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */
import model.MouvementStock;
import service.StockService;

import java.util.List;

public class StockController {

    private StockService service = new StockService();

    public void enregistrerEntree(MouvementStock m) throws Exception {
        service.enregistrerEntree(m);
    }

    public void enregistrerSortie(MouvementStock m) throws Exception {
        service.enregistrerSortie(m);
    }

    public List<MouvementStock> listerMouvements() {
        return service.listerMouvements();
    }
}
