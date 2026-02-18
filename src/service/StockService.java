/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ADMIN
 */
import DAO.MouvementStockDAO;
import model.MouvementStock;
import model.Produit;

import java.util.List;

public class StockService {

    private MouvementStockDAO DAO = new MouvementStockDAO();

    public void enregistrerEntree(MouvementStock m) throws Exception {
        if (m.getQuantite() <= 0) throw new Exception("Quantité doit être > 0.");
        m.setType(MouvementStock.Type.ENTREE);
        DAO.save(m);
    }

    public void enregistrerSortie(MouvementStock m) throws Exception {
        if (m.getQuantite() <= 0) throw new Exception("Quantité doit être > 0.");
        if (m.getProduit().getStockActuel() < m.getQuantite())
            throw new Exception("Stock insuffisant.");
        m.setType(MouvementStock.Type.SORTIE);
        DAO.save(m);
    }

    public List<MouvementStock> listerMouvements() {
        return DAO.findAll();
    }
}

