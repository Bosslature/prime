/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class LigneCommande {

    private int id;
    private Produit produit;
    private int quantite;
    private double prixUnitaire;

    // Constructeurs
    public LigneCommande() {}
    public LigneCommande(int id, Produit produit, int quantite, double prixUnitaire) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    // Calculer le montant de la ligne
    public double getMontant() { return quantite * prixUnitaire; }

    @Override
    public String toString() {
        return produit.getNom() + " x" + quantite + " = " + getMontant();
    }
}
