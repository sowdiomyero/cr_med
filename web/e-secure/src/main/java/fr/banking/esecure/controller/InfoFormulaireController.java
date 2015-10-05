/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.banking.esecure.controller;

import fr.banking.esecure.form.InfoFormulaireForm;
import fr.banking.esecure.form.Subscriber;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.metier.utils.PasswordGenerator;
import fr.esecure.banking.modele.client.entities.*;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mskane
 */
@Controller
public class InfoFormulaireController {

    @Autowired
    IEsecureMetier metier;
    private Demande demandeToFind;
    private InfoFormulaire infoFormulaireClient;

    int nombreVerif = 0;
    private Adresse adresse;
    Logger LOG = Logger.getLogger(InfoFormulaireController.class.getName());
    @Value(value = "${esecure.validation.formulaire.nbtentative}")
    int nbTentative;
    @Value(value = "${esecure.validation.formulaire.niveau}")
    int niveauValidation;

    //private   int compteurNbChance = 0;
    @RequestMapping(value = "clientform", method = RequestMethod.GET)
    public String InfoFormulaireFormverifInfoClient(@RequestParam(value = "idDemande") Long idDemande, Model model) {

        InfoFormulaireForm infoFormulaireForm = new InfoFormulaireForm();
        infoFormulaireForm.setNbTentativeAutorise(nbTentative);
        Subscriber subscriber = new Subscriber();
        subscriber.setRole(Subscriber.Role.ROLE_USER);
        model.addAttribute("subscriber", subscriber);
        try {

            //find damande by idDamande
            demandeToFind = (Demande) metier.findDemandeById(idDemande);

            if (demandeToFind.getEtatDemande().equals(Demande.STATE.REJECTED)) {
                infoFormulaireForm.setMsg("Cette demande a été déja rejetée!!");
                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_DEMANDE_ECHEC);
                model.addAttribute("infoFormulaireForm", infoFormulaireForm);
            } else if (demandeToFind.getEtatDemande().equals(Demande.STATE.ACCEPTED)) {
                infoFormulaireForm.setMsg("Cette demande a été déja validée!");
                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_DEMANDE_ACCEPTED);
                model.addAttribute("infoFormulaireForm", infoFormulaireForm);

            } else if (demandeToFind.getEtatDemande().equals(Demande.STATE.CANCELLED)) {
                infoFormulaireForm.setMsg("Cette demande a été déja annulée!!");
                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_DEMANDE_CANCELLED);
                model.addAttribute("infoFormulaireForm", infoFormulaireForm);

            } else {

                //RECUPERE  LES DONNEES CLIENTS  DE LA DEMANDE
                infoFormulaireForm.setNomClient(infoFormulaireForm.CrypteValeur(demandeToFind.getTransaction().getCarteBancaire().getClient().getNomClient()));
                infoFormulaireForm.setPrenomClient(infoFormulaireForm.CrypteValeur(demandeToFind.getTransaction().getCarteBancaire().getClient().getPrenomClient()));
                infoFormulaireForm.setEmail(infoFormulaireForm.CrypteValeur(demandeToFind.getTransaction().getCarteBancaire().getClient().getEmail()));

                //RECUPERE ADRESSE DE LA TRANSACTION
                adresse = new Adresse();
                adresse = demandeToFind.getTransaction().getAdresse();
                infoFormulaireForm.setCodePostal(infoFormulaireForm.CrypteValeur(adresse.getCodePostal()));
                infoFormulaireForm.setVille(infoFormulaireForm.CrypteValeur(adresse.getVille()));

                //RECUPERE INFO TRANSACTION DE LA DEMANDE
                infoFormulaireForm.setNomCommercant(demandeToFind.getTransaction().getNomCommercant());
                infoFormulaireForm.setMontantTransaction("" + demandeToFind.getTransaction().getMontant());
                infoFormulaireForm.setSiteWebCommercant("" + demandeToFind.getTransaction().getSiteWebCommercant());

                infoFormulaireForm.setEmailsplit("@" + PasswordGenerator.CryptLeftEmail(demandeToFind.getTransaction().getCarteBancaire().getClient().getEmail()));
                infoFormulaireForm.setEmailLeftCrypt(PasswordGenerator.CryptLeftEmail(demandeToFind.getTransaction().getCarteBancaire().getClient().getEmail()));;
                infoFormulaireForm.setNbVerif(0);// A la demande de la page
                model.addAttribute("infoFormulaireForm", infoFormulaireForm);
                demandeToFind.setEtatDemande(Demande.STATE.IN_PROGRESS);
                demandeToFind.getTransaction().setEtatTransaction(Transaction.TRANSACTION_STATES.PROCESSING);
                metier.updateDemande(demandeToFind);
                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_OK);
            }
        } catch (Exception e) {
            infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_EXCEPTION);
            infoFormulaireForm.setMsg("IL N'EXISTE PAS DE DEMANDE AYANT L'ID " + idDemande + "!!");
            LOG.log(Level.SEVERE, "IL N'EXISTE PAS DE DEMANDE AYANT L'ID " + idDemande, e);
        }
        return "clientform";

    }

    @RequestMapping(value = "clientform/verif", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/json; charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InfoFormulaireForm verifClientForm(@RequestBody @Valid InfoFormulaireForm infoFormulaireForm, HttpServletRequest request, Model m, BindingResult result) {

        infoFormulaireClient = new InfoFormulaire();
        infoFormulaireForm.setNbVerif(infoFormulaireForm.getNbVerif() + 1);
        nombreVerif = infoFormulaireForm.getNbVerif();
        //compteurNbChance = compteurNbChance + (infoFormulaireForm.getNbVerif()+1);
        if (result.hasErrors()) {
            infoFormulaireForm.setResultat(infoFormulaireForm.RETOUR_EXCEPTION);
            infoFormulaireForm.setMsg(" Erreurs survenues pendant la vérification des données");
            return infoFormulaireForm;
        } else {
            infoFormulaireForm.setMsg("");
            infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_OK);

            List<Adresse> adressClient;
            Adresse adressLivraison;
            adressLivraison = demandeToFind.getTransaction().getAdresse();
            Client client = demandeToFind.getTransaction().getCarteBancaire().getClient();
            adressClient = metier.findAdresseByClientId(client.getIdClient());

            Adresse adresseSaisie = new Adresse(0, infoFormulaireForm.getVilleSaisie(), null, 0, 0, infoFormulaireForm.getCodePostalSaisie());
            boolean isEqualAdress = verifAdresse(demandeToFind, adressClient, adresseSaisie);

            if (verifNomPrenomEmailClient(demandeToFind, infoFormulaireForm) && (isEqualAdress)) {
                infoFormulaireForm.setMsg("Vérification des données réussie");
                demandeToFind.setEtatDemande(Demande.STATE.ACCEPTED);
                demandeToFind.getTransaction().setEtatTransaction(Transaction.TRANSACTION_STATES.VALIDATED);

                infoFormulaireClient.setNomClient(infoFormulaireForm.getNomClientSaisie());
                infoFormulaireClient.setPrenomClient(infoFormulaireForm.getPrenomClientSaisie());
                infoFormulaireClient.setCodePostal(infoFormulaireForm.getCodePostalSaisie());
                infoFormulaireClient.setEmail(infoFormulaireForm.getEmailSaisie());
                infoFormulaireClient.setVille(infoFormulaireForm.getVilleSaisie());

                demandeToFind.setInfoFormulaire(infoFormulaireClient);
                metier.updateDemande(demandeToFind);

                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_OK);

                infoFormulaireForm.setMsgPrenomClientSaisie("");
                infoFormulaireForm.setMsgNomClientSaisie("");
                infoFormulaireForm.setMsgCodePostalSaisie("");
                infoFormulaireForm.setMsgVilleSaisie("");
                infoFormulaireForm.setMsgemailSaisie("");

                return infoFormulaireForm;

            } else {

                infoFormulaireForm.setMsg("Echec de la vérification des données\n"
                        + "il vous reste " + infoFormulaireForm.getNbTentativesRestant() + " chance pour valider le formulaire");
                if (!isEqualAdress) {
                    infoFormulaireForm.setMsgAdress("L'adresse Client doit être identique à l'adresse de livraison!");
                }

                infoFormulaireForm.setResultat(InfoFormulaireForm.RETOUR_EXCEPTION);

                if (infoFormulaireForm.getNbVerif() >= nbTentative) {
                    demandeToFind.setEtatDemande(Demande.STATE.REJECTED);
                    demandeToFind.getTransaction().setEtatTransaction(Transaction.TRANSACTION_STATES.REJECTED);

                    infoFormulaireClient.setNomClient(infoFormulaireForm.getNomClientSaisie());
                    infoFormulaireClient.setPrenomClient(infoFormulaireForm.getPrenomClientSaisie());
                    infoFormulaireClient.setCodePostal(infoFormulaireForm.getCodePostalSaisie());
                    infoFormulaireClient.setEmail(infoFormulaireForm.getEmailSaisie());
                    infoFormulaireClient.setVille(infoFormulaireForm.getVilleSaisie());

                    demandeToFind.setInfoFormulaire(infoFormulaireClient);
                    metier.updateDemande(demandeToFind);
                    infoFormulaireForm.setChampsIncorrectAndMsg();
                }

                return infoFormulaireForm;
            }

        }

    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "transaction/annulation", method = RequestMethod.GET)
    public String annulerTransaction(Model model) {
        if (nombreVerif < nbTentative) {
            demandeToFind.getTransaction().setEtatTransaction(Transaction.TRANSACTION_STATES.CANCELLED);
            demandeToFind.setEtatDemande(Demande.STATE.CANCELLED);
            metier.updateDemande(demandeToFind);
        }

        return "";
    }

    public boolean verifAdresse(Demande demande, List<Adresse> adressClient, Adresse saisie) {
        if (isAdresseClientEqualAdresseLivraison(adressClient, demande.getTransaction().getAdresse()) && niveauValidation == 1) {
            return true;
        } else if (isAdresseClientEqualAdresseLivraison(adressClient, saisie) && niveauValidation == 2) {
            // niveau validation 2
            List<Adresse> adLivraison = Arrays.asList(demande.getTransaction().getAdresse());
            return isAdresseClientEqualAdresseLivraison(adLivraison, saisie);
        }
        return false;
    }

    private boolean isAdresseClientEqualAdresseLivraison(List<Adresse> adressClient, Adresse adressLivraison) {

        for (Adresse adr : adressClient) {
            if (adr.equals(adressLivraison)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifNomPrenomEmailClient(Demande demande, InfoFormulaireForm infoFormulaireForm) {
        Client client = demande.getTransaction().getCarteBancaire().getClient();
        String nomClient = client.getNomClient();
        String prenomClient = client.getPrenomClient();
        String emailClient = client.getEmail();
        if (nomClient.equalsIgnoreCase(infoFormulaireForm.getNomClientSaisie()) && prenomClient.equalsIgnoreCase(infoFormulaireForm.getPrenomClientSaisie()) && emailClient.equalsIgnoreCase(infoFormulaireForm.getEmailSaisie())) {
            return true;
        }
        return false;
    }

}
