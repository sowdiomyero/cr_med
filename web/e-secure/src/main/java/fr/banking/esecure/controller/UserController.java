package fr.banking.esecure.controller;

import fr.banking.esecure.dto.BasicResponse;
import fr.banking.esecure.dto.EditUserDTO;
import fr.banking.esecure.form.EditUserForm;
import fr.banking.esecure.form.Subscriber;
import fr.esecure.banking.mail.sender.IEmailSender;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.metier.utils.PasswordGenerator;
import fr.esecure.banking.modele.client.entities.Compte;
import fr.esecure.banking.modele.client.entities.Role;
import fr.esecure.banking.modele.client.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/02/15 Time: 23:28 To change
 * this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    @Autowired
    IEsecureMetier metier;

    @Autowired
    IEmailSender mailSender;

    private long idUserSaisi = 0;
    private String loginSaisi = null;

    Logger LOG = Logger.getLogger(UserController.class.getName());

    @ModelAttribute("roles")
    public Subscriber.Role[] roles() {
        return Subscriber.Role.values();
    }

    @RequestMapping(value = "user/addUser", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Subscriber submittedFromData(@RequestBody @Valid Subscriber subscriber, HttpServletRequest request, Model m) {

        if (metier.isUserWithEmailExist(subscriber.getEmail().trim())) {
            subscriber.setMsg("Un utilisateur existe avec la même adresse email [" + subscriber.getEmail() + "] vous devez utiliser une autre adresse mail");
            subscriber.setResultat(Subscriber.RETOUR_EMAIL_INVALID);
            return subscriber;
        }
        String loginSplited = metier.getUserNameFromEmail(subscriber.getEmail());

        if (metier.isAccountWithLoginExist(loginSplited)) {
            subscriber.setMsg("Un utilisateur existe avec un login suivant : [" + loginSplited + "] vous devez utiliser une autre adresse mail");
            subscriber.setResultat(Subscriber.RETOUR_LOGIN_INVALID);
            return subscriber;
        }

        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            User user = new User();
            Role r = new Role(subscriber.getRole().name());
            user.addRole(r);
            user.setUserLogged(0);
            user.setUserMail(subscriber.getEmail());
            user.setUserName(subscriber.getNom());
            user.setUserPrenom(subscriber.getPrenom());
            User userCreated = (User) metier.createNewUserAccount(user);

            subscriber = new Subscriber();
            subscriber.setResultat(Subscriber.RETOUR_OK);
            subscriber.setMsg("Utilisateur crée avec succès avec les informations suivantes : [ Login =  " + userCreated.getCompte().getLogin() + " ]");
            m.addAttribute("subscriber", subscriber);
        } catch (Exception ex) {
            subscriber.setResultat(Subscriber.RETOUR_EXCEPTION);
            subscriber.setMsg("Une erreur est survenue pendant le traitement!!");
        } finally {
            return subscriber;
        }

    }

    @RequestMapping(value = "user/deleteUser", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteUser(@RequestParam(value = "idUser") Long idUser, Model model) {
        BasicResponse response = new BasicResponse();
        User userToFind = (User) metier.findEntityById(idUser, User.class);
        if(userToFind !=null && (userToFind.isUserInRole("ROLE_SUPER_ADMIN"))) {
            response.setMsg("Vous n'avez pas les droits de supprimer un utilisateur SUPER ADMINISTRATEUR " + idUser + " ]");
            response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            return response;
        }
        if (userToFind != null) {
            metier.delete(idUser, User.class);
            response.setMsg("L'utilisateur [" + userToFind.getUserName().toUpperCase() + " " + userToFind.getUserPrenom() + "] a été supprimé de la base de données avec succes");
            response.setResultat(BasicResponse.RETOUR_OK);
        } else {
            response.setMsg("Il n'existe aucun utilisateur ayant l'id [ " + idUser + " ]");
            response.setResultat(BasicResponse.RETOUR_ID_INVALID);
        }
        return response;

    }

    @RequestMapping(value = "user/getUser", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editUser(@RequestParam(value = "idUser") Long idUser) {

        EditUserDTO editUser = new EditUserDTO();
        User user = metier.findUserById(idUser);
        if (user == null) {
            editUser.setMsg("Le client son adresse ne doivent pas etre null.");
            editUser.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editUser;
        }

        idUserSaisi = idUser;

        editUser.setNom(user.getUserName());
        editUser.setPrenom(user.getUserPrenom());
        editUser.setEmail(user.getUserMail());
        editUser.setTelephone(user.getUserPhone());
        editUser.setLogin(user.getCompte().getLogin());
        editUser.setMsg("Visualisation de l'utilisateur passée avec succés");
        return editUser;
    }

    @RequestMapping(value = "user/editUser", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditUserForm editUserForm(@RequestBody @Valid EditUserForm editUserForm, HttpServletRequest request) {

        String nom = editUserForm.getNom();
        String prenom = editUserForm.getPrenom();
        String telephone = editUserForm.getTelephone();
        String mail = editUserForm.getEmail();

        User editUser = metier.findUserById(idUserSaisi);
        String nomUserSelected = editUser.getUserName();
        String prenomUserSelected = editUser.getUserPrenom();
        String emailUserSelected = editUser.getUserMail();
        String telephoneUserSelected = editUser.getUserPhone();

        if (!(nom.equals(nomUserSelected)) || !(prenom.equals(prenomUserSelected)) || !(telephone.equals(telephoneUserSelected)) || !(mail.equals(emailUserSelected))) {

            editUser.setUserName(nom);
            editUser.setUserPrenom(prenom);
            editUser.setUserMail(mail);
            editUser.setUserPhone(telephone);
            metier.updateUser(editUser);
            metier.sendAccountUpdated(editUser, "Modification des informations du compte");
            editUserForm.setMsg("Modification passée avec succés");
            editUserForm.setResultat(BasicResponse.RETOUR_OK);
            return editUserForm;
        } 
        else 
        {
            editUserForm.setMsg("Aucune modification des informations opérée");
            editUserForm.setResultat(BasicResponse.RETOUR_OK);
            return editUserForm;
        }

    }

    @RequestMapping(value = "user/getProfil", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editProfil(@RequestParam(value = "login") String login) {
        loginSaisi = login;
        EditUserDTO editUser = new EditUserDTO();
        Compte compte = metier.findAccountByLogin(login);
        long idCompte = compte.getIdCompte();
        User user = metier.findUserByIdCompte(idCompte);
        editUser.setNom(user.getUserName());
        editUser.setPrenom(user.getUserPrenom());
        editUser.setEmail(user.getUserMail());
        editUser.setTelephone(user.getUserPhone());
        editUser.setLogin(compte.getLogin());
        editUser.setPassword(compte.getPassword());
        editUser.setMsg("Visualisation de l'utilisateur passée avec succés");
        return editUser;
    }

    @RequestMapping(value = "user/changePassword", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditUserForm changePassword(@RequestBody @Valid EditUserForm editUserForm, HttpServletRequest request, Model m, BindingResult result) {
        Compte compte = metier.findAccountByLogin(editUserForm.getLogin());
        long idAcompte = compte.getIdCompte();
        User editUser = metier.findUserByIdCompte(idAcompte);
        String actuelPassword = compte.getPassword();
        String passwordSaisi = editUserForm.getPassword();
        String passwordSaisiCrypte = PasswordGenerator.crypter(passwordSaisi);

        String nouveauPassword = editUserForm.getNewPassword();
        String confirmPassword = editUserForm.getConfirmPassword();

        String nomUser = editUser.getUserName();
        String prenomUser = editUser.getUserPrenom();
        String telephoneUser = editUser.getUserPhone();
        String emailUser = editUser.getUserMail();

        //Les valeur issues da la soummission du formulaire
        String nomUserSaisi = editUserForm.getNom();
        String prenomUserSaisi = editUserForm.getPrenom();
        String telephoneUserSaisi = editUserForm.getTelephone();
        String emailUserSaisi = editUserForm.getEmail();

        if (!(nomUser.equals(nomUserSaisi)) || !(prenomUser.equals(prenomUserSaisi)) || !(telephoneUser.equals(telephoneUserSaisi)) || !(emailUser.equals(emailUserSaisi)) && nouveauPassword.length() == 0 && confirmPassword.length() == 0 && passwordSaisi.length() == 0) 
        {
            editUser.setUserName(nomUserSaisi);
            editUser.setUserPrenom(prenomUserSaisi);
            editUser.setUserMail(emailUserSaisi);
            editUser.setUserPhone(telephoneUserSaisi);

            metier.updateUser(editUser);
            editUserForm.setMsg("Modification des informations passées avec succés");
            editUserForm.setResultat(BasicResponse.RETOUR_OK);
            return editUserForm;
       }
        
        if (nouveauPassword.length() > 0 && confirmPassword.length() > 0 && passwordSaisi.length() > 0 && nouveauPassword.equals(confirmPassword) && actuelPassword.equals(passwordSaisiCrypte)) {
            String newPassword = PasswordGenerator.crypter(confirmPassword);
            compte.setPassword(newPassword);
            editUser.setUserName(nomUserSaisi);
            editUser.setUserPrenom(prenomUserSaisi);
            editUser.setUserMail(emailUserSaisi);
            editUser.setUserPhone(telephoneUserSaisi);
            compte.setUser(editUser);
            metier.updateCompte(compte);
            editUserForm.setMsg("Modification du mot de passe est passée avec succés.");
            editUserForm.setResultat(BasicResponse.RETOUR_OK_REDIRECT);
            request.getSession().invalidate();
            return editUserForm;
        } else {
            editUserForm.setMsg("Modification du mot de passe echouée.");
            editUserForm.setResultat(BasicResponse.RETOUR_DEMANDE_ECHEC);
            return editUserForm;
        }

    }


    @RequestMapping(value = "user/regenerePassword", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editUser(@RequestParam(value = "loginUser") String loginUser) {
        Compte compte = metier.findAccountByLogin(loginUser);
        String passwordGenerate = PasswordGenerator.genererDefaultPassword();

        String passwordGenerateCrypted = PasswordGenerator.crypter(passwordGenerate);
        compte.setPassword(passwordGenerateCrypted);
        User user = metier.findUserByIdCompte(compte.getIdCompte());
        metier.updateCompte(compte);

        metier.send(user, "Changement de mot de passe", passwordGenerate);
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setMsg("Password regénéré avec succés et envoyé à votre email.");
        editUserDTO.setResultat(BasicResponse.RETOUR_OK);
        return editUserDTO;
    }
}
