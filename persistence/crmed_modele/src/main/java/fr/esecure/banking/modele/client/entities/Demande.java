package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: sowdiomyero Date: 19/01/15 Time: 12:57 To
 * change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "demande", schema = "esecure")
    @NamedQueries({
    @NamedQuery(name = Demande.FIND_DEMANDE_BY_ID, query = "SELECT d FROM Demande d where d.idDemande=:idDemande")
    })
public class Demande extends AbstractDateEntity implements Serializable {

    
     public static final String FIND_DEMANDE_BY_ID = "findDemandeById";

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEMANDE")
    private Long idDemande;
    @Column(name = "ETAT_DEMANDE")
    @Enumerated(EnumType.STRING)
    private STATE etatDemande;

    public enum STATE {
        IN_PROGRESS,
        ACCEPTED,
        REJECTED,
        PENDING,
        UNDEFINED,
        CANCELLED;
    }
    @Column(name = "ID_ACCUSE_RECEPTION", updatable = false, insertable = false)
    private String idAccuseReception;

    @Column(name = "NUMERO_JETON")
    private int numeroJeton;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_ACCUSE_RECEPTION")
    private AccuseReception accuseReception;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_COMPTE_RENDU")
    private CompteRendu compteRendu;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_INFO_FORMULAIRE")
    private InfoFormulaire infoFormulaire;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ID_TRANSACTION")
    private Transaction transaction;

    public Demande() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Demande(AccuseReception accuseReception, CompteRendu compteRendu,
            InfoFormulaire infoFormulaire, Transaction transaction, int numeroJeton) {
        super();
        this.accuseReception = accuseReception;
        this.compteRendu = compteRendu;
        this.infoFormulaire = infoFormulaire;
        this.transaction = transaction;
        this.numeroJeton=numeroJeton;
    }

    public AccuseReception getAccuseReception() {
        return accuseReception;
    }

    public void setAccuseReception(AccuseReception accuseReception) {
        this.accuseReception = accuseReception;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public CompteRendu getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
    }

    public InfoFormulaire getInfoFormulaire() {
        return infoFormulaire;
    }

    public void setInfoFormulaire(InfoFormulaire infoFormulaire) {
        this.infoFormulaire = infoFormulaire;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the numeroJeton
     */
    public int getNumeroJeton() {
        return numeroJeton;
    }

    /**
     * @param numeroJeton the numeroJeton to set
     */
    public void setNumeroJeton(int numeroJeton) {
        this.numeroJeton = numeroJeton;
    }

    public String getIdAccuseReception() {
        return idAccuseReception;
    }

    public STATE getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(STATE etatDemande) {
        this.etatDemande = etatDemande;
    }
}
