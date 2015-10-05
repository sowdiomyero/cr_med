package fr.banking.esecure.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 12/02/15
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO extends BasicResponse {

    private Long idTransaction;

    private double montant;

    private int codeTransaction;


    private Date dateTransaction;

    private String nomCommercant;

    private String siteWebCommercant;

    private String etatTransaction;

    public TransactionDTO(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TransactionDTO() {
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getCodeTransaction() {
        return codeTransaction;
    }

    public void setCodeTransaction(int codeTransaction) {
        this.codeTransaction = codeTransaction;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
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
}
