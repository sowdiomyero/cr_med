/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*


package fr.banking.esecure.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

*/
/**
 *
 * @author mskane
 *//*

public class UserForm {
    
    @NotNull(message = "Vous devez renseigner le prenom")
    @Max(25)
    public String prenom;
    @NotNull(message = "Vous devez renseigner le Email")
    //@Email
    public String email;
    @NotNull(message = "Vous devez renseigner le prenom")
    @Max(25)
    public String nom;
    @NotNull(message = "Vous devez renseigner le prenom")
    @Pattern(regexp="(^$|[0-9]{10})")
    public String numeroTelephone;

    public UserForm() {
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
    
    
    
}
*/
