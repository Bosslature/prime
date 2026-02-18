/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
import java.util.Date;

public class MouvementStock {

    public enum Type { ENTREE, SORTIE }

    private int id;
    private Produit produit;
    private Type type;
    private int quantite;
    private Date dateMouvement;
    private String motif;

    // Constructeurs
    public MouvementStock() {}
    public MouvementStock(int id, Produit produit, Type type, int quantite, Date dateMouvement, String motif) {
        this.id = id;
        this.produit = produit;
        this.type = type;
        this.quantite = quantite;
        this.dateMouvement = dateMouvement;
        this.motif = motif;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Date getDateMouvement() { return dateMouvement; }
    public void setDateMouvement(Date dateMouvement) { this.dateMouvement = dateMouvement; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    @Override
    public String toString() {
        return produit.getNom() + " - " + type + " : " + quantite;
    }
}
