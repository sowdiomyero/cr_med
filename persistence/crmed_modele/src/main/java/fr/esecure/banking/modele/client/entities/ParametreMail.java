/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author mskane
 */
@Entity
@Table(name = "parametremail", schema = "esecure")
public class ParametreMail extends AbstractDateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PARAMETREMAIL")
    private Long idParametreMail;

    @Enumerated(EnumType.STRING)
    private STATE typeCompte;

    public enum STATE {

        POP,
        IMAP,
        SMTP,

    }

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SERVEUR_MAIL_ENTRANT")
    private String serveurMailEntrant;

    @Column(name = "SERVEUR_MAIL_SORTANT")
    private String serveurMailSortant;
    
    @Column(name = "NOM_UTILISATEUR")
    private String nomUtilisateur;

    @Column(name = "ADRESSE_MESSAGERIE")
    private String adresseMessagerie;

    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "PORT_SMTP")
    private String portSmtp;
    
    @Column(name = "PORT_POP3")
    private String portPop3;
    
    public ParametreMail() {
    }


    public ParametreMail(STATE typeCompte, String email, String serveurMailEntrant, String serveurMailSortant, String nomUtilisateur, String adresseMessagerie, String password, String portSmtp, String portPop3) {
        this.typeCompte = typeCompte;
        this.email = email;
        this.serveurMailEntrant = serveurMailEntrant;
        this.serveurMailSortant = serveurMailSortant;
        this.nomUtilisateur = nomUtilisateur;
        this.adresseMessagerie = adresseMessagerie;
        this.password = password;
        this.portSmtp = portSmtp;
        this.portPop3 = portPop3;
    }

    public Long getIdParametreMail() {
        return idParametreMail;
    }

    public void setIdParametreMail(Long idParametreMail) {
        this.idParametreMail = idParametreMail;
    }

    public STATE getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(STATE typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServeurMailEntrant() {
        return serveurMailEntrant;
    }

    public void setServeurMailEntrant(String serveurMailEntrant) {
        this.serveurMailEntrant = serveurMailEntrant;
    }

    public String getServeurMailSortant() {
        return serveurMailSortant;
    }

    public void setServeurMailSortant(String serveurMailSortant) {
        this.serveurMailSortant = serveurMailSortant;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getAdresseMessagerie() {
        return adresseMessagerie;
    }

    public void setAdresseMessagerie(String adresseMessagerie) {
        this.adresseMessagerie = adresseMessagerie;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortSmtp() {
        return portSmtp;
    }

    public void setPortSmtp(String portSmtp) {
        this.portSmtp = portSmtp;
    }

    public String getPortPop3() {
        return portPop3;
    }

    public void setPortPop3(String portPop3) {
        this.portPop3 = portPop3;
    }

    
 
    
}
