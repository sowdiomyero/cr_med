package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carte_bancaire", schema = "esecure")
public class CarteBancaire extends AbstractDateEntity implements Serializable {

    private static long serialVersionUID = 1L;

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CARTE_BANCAIRE")
    private Long idCarte;
    @Column(name = "NUM_CARTE")
    private String numCarte;
    @Column(name = "DATE_EXPIR")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateExpir;
    @Column(name = "CRYPTO")
    private int crypto;
    @Column(name = "TYPE_CARTE")
    private String typeCarte;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_CLIENT")
    private Client client;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carteBancaire")
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public CarteBancaire() {
        super();
    }

    public CarteBancaire(String numCarte, Date dateExpir, int crypto,
                         String typeCarte, Client client) {
        super();
        this.numCarte = numCarte;
        this.dateExpir = dateExpir;
        this.crypto = crypto;
        this.typeCarte = typeCarte;
        this.client = client;
    }

    public String getNumCarte() {
        return numCarte;
    }


    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }


    public int getCrypto() {
        return crypto;
    }


    public void setCrypto(int crypto) {
        this.crypto = crypto;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Long getIdCarte() {
        return idCarte;
    }


    public void setIdCarte(Long idCarte) {
        this.idCarte = idCarte;
    }

    public Date getDateExpir() {
        return dateExpir;
    }


    public void setDateExpir(Date dateExpir) {
        this.dateExpir = dateExpir;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction tr) {
        if (transactions != null) {
            transactions.add(tr);
        } else {
            transactions = new ArrayList<Transaction>();
            transactions.add(tr);
        }
    }

}
