/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking;

import fr.esecure.banking.modele.client.entities.Transaction;

/**
 *
 * @author sniang
 */
public class TransactionRow {
    private String etat;
    private String prenom;
    private String nom;
    private String carte;
    private String montant;
    private String date;

    public TransactionRow() {
    }

    public TransactionRow(Transaction tr) {
        if(tr!=null){
            this.date =tr.getDateTransaction().toString();
            this.etat=tr.getEtatTransaction().name() ;
            if(tr.getCarteBancaire() != null && tr.getCarteBancaire().getClient() !=null) {
                this.prenom =tr.getCarteBancaire().getClient().getPrenomClient();
                this.nom =tr.getCarteBancaire().getClient().getNomClient();
            }
            this.montant =String.valueOf(tr.getMontantBrute());
            if(tr.getCarteBancaire() !=null)
            this.carte =tr.getCarteBancaire().getNumCarte();

        }
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomClient) {
        this.prenom = prenomClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomClient) {
        this.nom = nomClient;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String numeroCarte) {
        this.carte = numeroCarte;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateTransaction) {
        this.date = dateTransaction;
    }
    public static String[] getHeaders() {
        final String[] header = new String[] { "prenom", "nom", "carte", "montant", "etat", "date"};
        return header;
    }
    
}
