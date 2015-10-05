package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: sowdiomyero Date: 19/01/15 Time: 12:58 To
 * change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "info_formulaire", schema = "esecure")
public class InfoFormulaire extends AbstractDateEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INFO_FORMULAIRE")
    private Long idFormulaire;
    @Column(name = "NOM_CLIENT")
    private String nomClient;
    @Column(name = "PRENOM_CLIENT")
    private String prenomClient;
    @Column(name = "NUMERO_CARTE_BANCAIRE")
    private String numeroCarteBancaire;
    @Column(name = "NUMERO_COMPTE")
    private String numeroCompte;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NUMERO_RUE")
    private String numeroRue;
    @Column(name = "NOM_RUE")
    private String nomRue;
    @Column(name = "TYPE_RESIDENCE")
    private String typeResidence;
    @Column(name = "NOM_RESIDENCE")
    private String nomResidence;
    @Column(name = "NUMERO_ETAGE")
    private int numeroEtage;
    @Column(name = "CODE_POSTAL")
    private String codePostal;
    @Column(name = "VILLE")
    private String ville;

    public InfoFormulaire() {
        super();
        // TODO Auto-generated constructor stub
    }

    public InfoFormulaire(String nomClient, String prenomClient, String numeroCarteBancaire, String numeroCompte, String email, String numeroRue, String nomRue, String typeResidence, String nomResidence, String codePostal, String ville) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.numeroCarteBancaire = numeroCarteBancaire;
        this.numeroCompte = numeroCompte;
        this.email = email;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.typeResidence = typeResidence;
        this.nomResidence = nomResidence;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getIdFormulaire() {
        return idFormulaire;
    }

    public void setIdFormulaire(Long idFormulaire) {
        this.idFormulaire = idFormulaire;
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
     * @return the numeroCarteBancaire
     */
    public String getNumeroCarteBancaire() {
        return numeroCarteBancaire;
    }

    /**
     * @param numeroCarteBancaire the numeroCarteBancaire to set
     */
    public void setNumeroCarteBancaire(String numeroCarteBancaire) {
        this.numeroCarteBancaire = numeroCarteBancaire;
    }

    /**
     * @return the numeroCompte
     */
    public String getNumeroCompte() {
        return numeroCompte;
    }

    /**
     * @param numeroCompte the numeroCompte to set
     */
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
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
     * @return the numeroRue
     */
    public String getNumeroRue() {
        return numeroRue;
    }

    /**
     * @param numeroRue the numeroRue to set
     */
    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    /**
     * @return the nomRue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * @param nomRue the nomRue to set
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    /**
     * @return the typeResidence
     */
    public String getTypeResidence() {
        return typeResidence;
    }

    /**
     * @param typeResidence the typeResidence to set
     */
    public void setTypeResidence(String typeResidence) {
        this.typeResidence = typeResidence;
    }

    /**
     * @return the numeroEtage
     */
    public int getNumeroEtage() {
        return numeroEtage;
    }

    /**
     * @param numeroEtage the numeroEtage to set
     */
    public void setNumeroEtage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
    }

    /**
     * @return the nomResidence
     */
    public String getNomResidence() {
        return nomResidence;
    }

    /**
     * @param nomResidence the nomResidence to set
     */
    public void setNomResidence(String nomResidence) {
        this.nomResidence = nomResidence;
    }

    /**
     * @return the codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

}
