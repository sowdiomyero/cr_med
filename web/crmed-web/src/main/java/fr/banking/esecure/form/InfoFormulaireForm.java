/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.banking.esecure.form;

import fr.banking.esecure.dto.BasicResponse;
import fr.esecure.banking.modele.client.entities.Adresse;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author mskane
 */
public class InfoFormulaireForm extends BasicResponse{




    @NotNull
    @NotEmpty
    private String nomClientSaisie;
    private String nomClient;
    private int nbTentativeAutorise;
    @NotNull
    @NotEmpty
    private String prenomClientSaisie;
    
    private String prenomClient;

    private String email;
    @NotNull
    @NotEmpty
    private String emailSaisie;

    @NotNull
    @NotEmpty
    private String codePostalSaisie;
    private String codePostal;

    @NotNull
    @NotEmpty
    private String villeSaisie;
    private String ville;

    private String nomCommercant;
    private String montantTransaction;
    private String siteWebCommercant;
    private String emailsplit;
    private String emailLeftCrypt;
    
    //message validateteurs
    private String msgNomClientSaisie;
    private String msgPrenomClientSaisie;
    private String msgemailSaisie;
    private String msgCodePostalSaisie;
    private String msgVilleSaisie;
    
     private String etatDemande;
     private String msgAdress;
    
    private int nbVerif;
    
    private List<Adresse> adresse;

    public List<Adresse> getAdresse() {
        return adresse;
    }

    public void setAdresse(List<Adresse> adresse) {
        this.adresse = adresse;
    }

    
    public String getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    
    public int getNbVerif() {
        return nbVerif;
    }
    public int getNbTentativesRestant() {
        return (nbTentativeAutorise - nbVerif);
    }

    public void setNbVerif(int nbChanceVerif) {
        this.nbVerif = nbChanceVerif;
    }
   
    public String getEmailLeftCrypt() {
        return emailLeftCrypt;
    }

    public void setEmailLeftCrypt(String emailLeftCrypt) {
        this.emailLeftCrypt = emailLeftCrypt;
    }

    public String getNomClientSaisie() {
        return nomClientSaisie;
    }

    public void setNomClientSaisie(String nomClientSaisie) {
        this.nomClientSaisie = nomClientSaisie;
    }

    public String getPrenomClientSaisie() {
        return prenomClientSaisie;
    }

    public void setPrenomClientSaisie(String prenomClientSaisie) {
        this.prenomClientSaisie = prenomClientSaisie;
    }

    public String getEmailSaisie() {
        return emailSaisie;
    }

    public void setEmailSaisie(String emailSaisie) {
        this.emailSaisie = emailSaisie;
    }

    public String getCodePostalSaisie() {
        return codePostalSaisie;
    }

    public void setCodePostalSaisie(String codePostalSaisie) {
        this.codePostalSaisie = codePostalSaisie;
    }

    public String getVilleSaisie() {
        return villeSaisie;
    }

    public void setVilleSaisie(String villeSaisie) {
        this.villeSaisie = villeSaisie;
    }
    

    public String getEmailsplit() {
        return emailsplit;
    }

    public void setEmailsplit(String emailsplit) {
        this.emailsplit = emailsplit;
    }

    public String getSiteWebCommercant() {
        return siteWebCommercant;
    }

    public void setSiteWebCommercant(String siteWebCommercant) {
        this.siteWebCommercant = siteWebCommercant;
    }

    public String getNomCommercant() {
        return nomCommercant;
    }

    public void setNomCommercant(String nomCommercant) {
        this.nomCommercant = nomCommercant;
    }

    public String getMontantTransaction() {
        return montantTransaction;
    }

    public void setMontantTransaction(String montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMsgNomClientSaisie() {
        return msgNomClientSaisie;
    }

    public void setMsgNomClientSaisie(String msgNomClientSaisie) {
        this.msgNomClientSaisie = msgNomClientSaisie;
    }

    public String getMsgPrenomClientSaisie() {
        return msgPrenomClientSaisie;
    }

    public void setMsgPrenomClientSaisie(String msgPrenomClientSaisie) {
        this.msgPrenomClientSaisie = msgPrenomClientSaisie;
    }

    public String getMsgemailSaisie() {
        return msgemailSaisie;
    }

    public void setMsgemailSaisie(String msgemailSaisie) {
        this.msgemailSaisie = msgemailSaisie;
    }

    public String getMsgCodePostalSaisie() {
        return msgCodePostalSaisie;
    }

    public void setMsgCodePostalSaisie(String msgCodePostalSaisie) {
        this.msgCodePostalSaisie = msgCodePostalSaisie;
    }

    public String getMsgVilleSaisie() {
        return msgVilleSaisie;
    }

    public void setMsgVilleSaisie(String msgVilleSaisie) {
        this.msgVilleSaisie = msgVilleSaisie;
    }

    public String getMsgAdress() {
        return msgAdress;
    }

    public void setMsgAdress(String msgAdress) {
        this.msgAdress = msgAdress;
    }
    
    

    //verifie la corespondance des données que le client à saisie
   public boolean isDataCorrect() {
        
        if (getPrenomClient().equalsIgnoreCase(getPrenomClientSaisie()) && getNomClient().equalsIgnoreCase(getNomClientSaisie()) && codePostal.equalsIgnoreCase(getCodePostalSaisie())
                && getVille().equalsIgnoreCase(getVilleSaisie()) && getEmail().equalsIgnoreCase(emailSaisie)) {
            return true;
        }

        return false;
    }
   //vide les champs incorrects et set les messages
    public void setChampsIncorrectAndMsg() {
        if (!(getPrenomClient().equals(getPrenomClientSaisie()))) {
            setPrenomClientSaisie("");
            setMsgPrenomClientSaisie("Prénom incorrect");
        }
        if (!(getNomClient().equals(getNomClientSaisie()))) {
            setNomClientSaisie("");
            setMsgNomClientSaisie("Nom incorrect");
        }
        if (!(getCodePostal().equals(getCodePostalSaisie()))) {
            setCodePostalSaisie("");
            setMsgCodePostalSaisie("code postal incorrect");
        }
        if (!(getVille().equals(getVilleSaisie()))) {
            setVilleSaisie("");
            setMsgVilleSaisie("Ville incorrect");
        }
        if (!(getEmail().equals(getEmailSaisie()))) {
            setEmailSaisie("");
            setMsgemailSaisie("Email incorrect");
        }
    }

    public int getNbTentativeAutorise() {
        return nbTentativeAutorise;
    }

    public void setNbTentativeAutorise(int nbTentativeAutorise) {
        this.nbTentativeAutorise = nbTentativeAutorise;
    }
    
    public String CrypteValeur(String valeur){
        String valeurCryptee="";
        for(int i=0;i<valeur.length(); i++ )
        {       valeurCryptee+="*";
    }
        return  valeurCryptee;
    }
}
