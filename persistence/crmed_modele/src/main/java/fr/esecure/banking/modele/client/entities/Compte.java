/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sniang
 */
@Entity
@Table(name="compte", schema="esecure")

public class Compte extends AbstractDateEntity implements Serializable {


    public Compte() {
        super();
    }
public Compte(Date dateCreation, int etatCompte, String login, String password) {
    
    super.getDateCreation();
    this.etatCompte=etatCompte;
    this.login=login;
    this.password=password;    
}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_COMPTE")
    private Long idCompte;
//    @Column(name = "DATE_CREATION")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Date dateCreation;
    @Column(name="ETAT")
    private int etatCompte;
    @Column(name="LOGIN")
    private String login;
    @Column(name="PASSWORD")
    private String password;
    @OneToOne(mappedBy = "compte")
    @JoinColumn(name = "ID_USER")
    private User user;

    /**
     * @return the idCompte
     */
    public Long getIdCompte() {
        return idCompte;
    }

    /**
     * @param idCompte the idCompte to set
     */
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    /**
     * @return the dateCreation
     */
//    public Date getDateCreation() {
//        return dateCreation;
//    }

    /**
     * @param dateCreation the dateCreation to set
     */
//    public void setDateCreation(Date dateCreation) {
//        this.dateCreation = dateCreation;
//    }

    /**
     * @return the etatCompte
     */
    public int getEtatCompte() {
        return etatCompte;
    }

    /**
     * @param etatCompte the etatCompte to set
     */
    public void setEtatCompte(int etatCompte) {
        this.etatCompte = etatCompte;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
