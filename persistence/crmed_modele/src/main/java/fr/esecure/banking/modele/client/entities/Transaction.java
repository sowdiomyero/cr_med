package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 19/01/15
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "carte_transaction", schema = "esecure")
@NamedQueries({

        @NamedQuery(name = Transaction.FIND_ALL_TRANSACTION, query = "SELECT TR FROM Transaction TR"),
        @NamedQuery(name = Transaction.FIND_TRANSACTION_BY_ID, query = "SELECT TR FROM Transaction TR where TR.idTransaction=:id"),
        @NamedQuery(name = Transaction.FIND_TRANSACTION_BY_STATE, query = "SELECT TR FROM Transaction TR WHERE TR.etatTransaction=:state"),
        @NamedQuery(name = Transaction.FIND_TRANSACTIONS_BETWEEN_DATE, query = "SELECT TR FROM Transaction TR WHERE TR.dateTransaction BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Transaction.FIND_TRANSACTIONS_TODAY, query = "SELECT TR FROM Transaction TR WHERE TR.dateTransaction >=:dateToday"),

})
public class Transaction extends AbstractDateEntity implements Serializable {

    public enum TRANSACTION_STATES {
        PROCESSING,
        VALIDATED,
        CANCELLED,
        REJECTED
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_TRANSACTION = "findAllTransactions";
    public static final String FIND_TRANSACTION_BY_ID = "findTransactionById";
    public static final String FIND_TRANSACTION_BY_STATE = "findTransactionsByState";
    public static final String FIND_CLIENT_BY_ID_TRANSACTION = "findClientByIdTransaction";
    public static final String FIND_TRANSACTIONS_BETWEEN_DATE = "findTransactionsBetweenDate";
    public static final String FIND_TRANSACTIONS_TODAY = "findTransactionsToday";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANSACTION")
    private Long idTransaction;
    @Column(name = "MONTANT")
    private double montant;
    @Column(name = "CODE_TRANSACTION")
    private int codeTransaction;
    @Column(name = "DATE_TRANSACTION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTransaction;
    @Column(name = "NOM_COMMERCANT")
    private String nomCommercant;
    @Column(name = "SITE_WEB_COMMERCANT")
    private String siteWebCommercant;
    @Column(name = "HREF_LOGO_COMMERCANT")
    private String hrefLogoCommercant;
    @Column(name = "ETAT")
    @Enumerated(EnumType.STRING)
    private TRANSACTION_STATES etatTransaction;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_CARTE_BANCAIRE", nullable = false)
    private CarteBancaire carteBancaire;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ADRESSE")
    public Adresse adresse;

    @Column(name = "MONTANT_BRUTE")
    private String montantBrute;
    @Column(name = "DEVISE")
    private String devise;

    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Transaction(double montant, int codeTransaction,
                       Date dateTransaction, String nomCommercant, String siteWebCommercant, TRANSACTION_STATES etatTransaction, CarteBancaire carteBancaire, Adresse adresse) {
        super();
        this.montant = montant;
        this.codeTransaction = codeTransaction;
        this.dateTransaction = dateTransaction;
        this.nomCommercant = nomCommercant;
        this.siteWebCommercant = siteWebCommercant;
        this.etatTransaction = etatTransaction;
        this.carteBancaire = carteBancaire;
        this.adresse = adresse;
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

    public CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    public void setCarteBancaire(CarteBancaire carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the nomCommercant
     */
    public String getNomCommercant() {
        return nomCommercant;
    }

    /**
     * @param nomCommercant the nomCommercant to set
     */
    public void setNomCommercant(String nomCommercant) {
        this.nomCommercant = nomCommercant;
    }

    /**
     * @return the siteWebCommercant
     */
    public String getSiteWebCommercant() {
        return siteWebCommercant;
    }

    /**
     * @param siteWebCommercant the siteWebCommercant to set
     */
    public void setSiteWebCommercant(String siteWebCommercant) {
        this.siteWebCommercant = siteWebCommercant;
    }

    /**
     * @return the dateTransaction
     */
    public Date getDateTransaction() {
        return dateTransaction;
    }

    /**
     * @param dateTransaction the dateTransaction to set
     */
    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }


    /**
     * @return the etatTransaction
     */
    public TRANSACTION_STATES getEtatTransaction() {
        return etatTransaction;
    }

    /**
     * @param etatTransaction the etatTransaction to set
     */
    public void setEtatTransaction(TRANSACTION_STATES etatTransaction) {
        this.etatTransaction = etatTransaction;
    }

    public String getHrefLogoCommercant() {
        return hrefLogoCommercant;
    }

    public void setHrefLogoCommercant(String hrefLogoCommercant) {
        this.hrefLogoCommercant = hrefLogoCommercant;
    }

    public String getMontantBrute() {
        if(montantBrute == null)
            return  String.valueOf(montant);
        return montantBrute;
    }

    public void setMontantBrute(String montantBrute) {
        this.montantBrute = montantBrute;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public static String getCurrency(String value){
        if(value !=null && value.trim().length() >0)
            return value.replaceAll("[^\\$\\£\\€]+", "");
        return "";
    }

    public static String getPriceValue(String value){
        if(value !=null && value.trim().length() >0)
            return value.replaceAll("[^\\d.]+", "");
        return "";
    }

    /**
     * Prend en compte les monnaies suivantes : euro, dollar canadien, dollar americain, yen japonais, yen chinois
     * @return
     */
    public String getCurrency(){
        String response ="";
        if(montantBrute !=null && montantBrute.trim().length() >0)
            response= montantBrute.replaceAll("[^\\($|€|£|¥|EURO|USD|JPY|CAD|CNY|GBP)]+", "");
            //response= montantBrute.replaceAll("[^\\$\\£\\€]+", "");
        return response;
    }

    public String getPriceValue(){
        if(montantBrute !=null && montantBrute.trim().length() >0)
        return montantBrute.replaceAll("[^\\d.]+", "");
        return "";
    }
}
