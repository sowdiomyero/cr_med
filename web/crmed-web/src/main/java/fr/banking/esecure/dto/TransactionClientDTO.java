/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.banking.esecure.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sniang
 */
public class TransactionClientDTO extends BasicResponse {

    private Long idTransaction;

    private String montantTransaction;

    private int codeTransaction;

    private String dateTransaction;

    private String nomCommercant;

    private String siteWebCommercant;

    private String etatTransaction;
    
    private String nomClient;
    
    private String prenomClient;
    
    private String numCompte;
    
    private String numCarte;
    
    private String adresseClient;
    
    private String nomConseiller;

    private String adresseCommercant;
    
    private String email;
    
    private List<TransactionClientDTO> transactions = new ArrayList<TransactionClientDTO>();

    public TransactionClientDTO() {
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    

    public int getCodeTransaction() {
        return codeTransaction;
    }

    public void setCodeTransaction(int codeTransaction) {
        this.codeTransaction = codeTransaction;
    }

   

    public String getNomCommercant() {
        return nomCommercant;
    }

    public void setNomCommercant(String nomCommercant) {
        this.nomCommercant = nomCommercant;
    }

    public String getSiteWebCommercant() {
        return siteWebCommercant;
    }

    public void setSiteWebCommercant(String siteWebCommercant) {
        this.siteWebCommercant = siteWebCommercant;
    }

    public String getEtatTransaction() {
        return etatTransaction;
    }

    public void setEtatTransaction(String etatTransaction) {
        this.etatTransaction = etatTransaction;
    }

    /**
     * @return the nomClient
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * @param nomClient the nomClient to set
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * @return the prenomClient
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * @param prenomClient the prenomClient to set
     */
    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    /**
     * @return the numCompte
     */
    public String getNumCompte() {
        return numCompte;
    }

    /**
     * @param numCompte the numCompte to set
     */
    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    /**
     * @return the numCarte
     */
    public String getNumCarte() {
        return numCarte;
    }

    /**
     * @param numCarte the numCarte to set
     */
    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }



    /**
     * @return the nomConseiller
     */
    public String getNomConseiller() {
        return nomConseiller;
    }

    /**
     * @param nomConseiller the nomConseiller to set
     */
    public void setNomConseiller(String nomConseiller) {
        this.nomConseiller = nomConseiller;
    }

 

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the montantTransaction
     */
    public String getMontantTransaction() {
        return montantTransaction;
    }

    /**
     * @param montantTransaction the montantTransaction to set
     */
    public void setMontantTransaction(String montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    /**
     * @return the dateTransaction
     */
    public String getDateTransaction() {
        return dateTransaction;
    }

    /**
     * @param dateTransaction the dateTransaction to set
     */
    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    /**
     * @return the adresseCommercant
     */
    public String getAdresseCommercant() {
        return adresseCommercant;
    }

    /**
     * @param adresseCommercant the adresseCommercant to set
     */
    public void setAdresseCommercant(String adresseCommercant) {
        this.adresseCommercant = adresseCommercant;
    }

    /**
     * @return the adresseClient
     */
    public String getAdresseClient() {
        return adresseClient;
    }

    /**
     * @param adresseClient the adresseClient to set
     */
    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    /**
     * @return the transactions
     */
    public List<TransactionClientDTO> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<TransactionClientDTO> transactions) {
        this.transactions = transactions;
    }

}
