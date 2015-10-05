/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.modele.client.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author sniang
 */
@Entity
@Table(name = "user", schema = "esecure")
@NamedQueries({

        @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM User u where u.userMail=:userMail"),
        @NamedQuery(name = User.FIND_USER_BY_ID, query = "SELECT u FROM User u where u.idUser=:idUser"),
        @NamedQuery(name = User.FIND_USER_BY_ID_COMPTE, query = "SELECT u FROM User u where u.idCompte=:idCompte"),
        @NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u"),
})
public class User extends AbstractDateEntity implements Serializable {
    public static final String FIND_USER_BY_EMAIL = "findUserByEmail";

    public static final String FIND_ALL_USERS = "findAllTUsers";
    
    public static final String FIND_USER_BY_ID= "findUserById";
    public static final String  FIND_USER_BY_ID_COMPTE= "findUserByIdCompte";

    public User() {
        super();
    }

   
    public User(String userMail, int userLogged, String userNom, String userPrenom, String userPhone) {
        this.userMail = userMail;
        this.userLogged = userLogged;
        this.userName = userNom;
        this.userPrenom = userPrenom;
        this.userPhone = userPhone;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "ID_COMPTE", updatable = false, insertable = false)
    private Long idCompte;
    @Column(name = "USER_MAIL")
    private String userMail;
    @Column(name = "USER_LOGGED")
    private int userLogged;
    @Column(name = "USER_NOM")
    private String userName;
    @Column(name = "USER_PRENOM")
    private String userPrenom;
    @Column(name = "USER_PHONE")
    private String userPhone;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private List<Role> roles;


    /**
     * @return the userMail
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * @param userMail the userMail to set
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * @return the userLogged
     */
    public int getUserLogged() {
        return userLogged;
    }

    /**
     * @param userLogged the userLogged to set
     */
    public void setUserLogged(int userLogged) {
        this.userLogged = userLogged;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userPrenom
     */
    public String getUserPrenom() {
        return userPrenom;
    }

    /**
     * @param userPrenom the userPrenom to set
     */
    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    /**
     * @return the userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone the userPhone to set
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return the compte
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     * @param compte the compte to set
     */
    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles != null)
            this.roles.add(role);
        else {
            this.roles = new ArrayList<Role>();
            this.roles.add(role);
        }
        
    }

    public void addRole(Role role, boolean clearBeforeAdd) {
        if (this.roles != null && clearBeforeAdd) {
            this.roles.clear();
            this.roles.add(role);
        } else {
            if (this.roles != null)
                this.roles.add(role);
            else {
                this.roles = new ArrayList<Role>();
                this.roles.add(role);
            }
        }
        ;
    }

    public boolean isUserInRole(String roleName) {

       for(Role r : roles){
           if(r.getNameRole().equalsIgnoreCase(roleName)){
              return true;
           }
       }
        return false;
    }


    /**
     * @return the idUser
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdCompte() {
        return idCompte;
    }


}
