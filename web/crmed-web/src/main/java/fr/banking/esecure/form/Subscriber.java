package fr.banking.esecure.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class Subscriber {


    public static final int RETOUR_OK=200;
    public static final int RETOUR_EXCEPTION=-100;
    public static final int RETOUR_EMAIL_INVALID=100;
    public static final int RETOUR_LOGIN_INVALID=300;

    @NotNull @NotEmpty
    private String nom;
    @Email
    private String email;
    @NotNull @NotEmpty
    private String prenom;

    private String msg;
    private int resultat;

    private Role role;


    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Subscriber [name=" + nom
                + ", prenom=" + prenom + "]";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }
}
