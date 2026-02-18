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

public class Commande {

    public enum Etat { EN_COURS, VALIDE, ANNULE }

    private int id;
    private Date dateCommande;
    private Etat etat;
    private double total;
    private Utilisateur utilisateur;

    // Constructeurs
    public Commande() {}
    public Commande(int id, Date dateCommande, Etat etat, double total, Utilisateur utilisateur) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.total = total;
        this.utilisateur = utilisateur;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }

    public Etat getEtat() { return etat; }
    public void setEtat(Etat etat) { this.etat = etat; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    @Override
    public String toString() { return "Commande #" + id; }
}