package fr.banking.esecure.controller;

import fr.banking.esecure.dto.BasicResponse;
import fr.banking.esecure.form.BanqueForm;
import fr.banking.esecure.form.MessagerieForm;
import fr.banking.esecure.form.RapportForm;
import fr.banking.esecure.form.Subscriber;
import fr.esecure.banking.metier.IEsecureMetier;
import fr.esecure.banking.modele.client.entities.Banque;
import fr.esecure.banking.modele.client.entities.MessagerieParam;
import fr.esecure.banking.modele.client.entities.ParamAcces;
import fr.esecure.banking.modele.client.entities.RapportParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/02/15 Time: 23:28 To change
 * this template use File | Settings | File Templates.
 */
@Controller
public class SettingsController {

    @Autowired
    IEsecureMetier metier;


    Banque banque=null;

    Logger LOG = Logger.getLogger(SettingsController.class.getName());

    @ModelAttribute("securites")
    public MessagerieForm.SECURITY_TYPE[] securites() {
        return MessagerieForm.SECURITY_TYPE.values();
    }
    @ModelAttribute("frequencies")
    public RapportForm.FREQUENCIES[] frequencies() {
        return RapportForm.FREQUENCIES.values();
    }
    @ModelAttribute("formats")
    public RapportForm.FORMAT_TYPE[] formats() {
        return RapportForm.FORMAT_TYPE.values();
    }
    @ModelAttribute("typeDonnees")
    public RapportForm.TRANSACTION_TYPE[] typeDonnees() {
        return RapportForm.TRANSACTION_TYPE.values();
    }


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public
    String getEsecureSettings(Model model) {
        MessagerieForm messagerieForm = new MessagerieForm();
        BanqueForm banqueForm = new BanqueForm();
        RapportForm rapportForm = new RapportForm();

        banque = metier.findCurrentBanque();
        if(banque != null){
           // paramAcces=banque.getParamAcces();
            if(banque.getParamAcces() != null)  {
                messagerieForm=remplirMessagerieForm(banque.getParamAcces());
                rapportForm= remplirRapport(banque.getParamAcces());
            }
            banqueForm= remplirBanque(banque);
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setRole(Subscriber.Role.ROLE_USER);

        model.addAttribute("subscriber", subscriber);
        model.addAttribute("messagerieForm", messagerieForm);
        model.addAttribute("banqueForm", banqueForm);
        model.addAttribute("rapportForm", rapportForm);

        return "settings";
    }

    private BanqueForm remplirBanque(Banque pbanque) {
        BanqueForm banqueForm = new BanqueForm();
        if(banque != null){
        banqueForm.setIdBanque(pbanque.getIdBanque().toString());
        banqueForm.setCigle(pbanque.getCigleBanque());
        banqueForm.setCodeBanque(pbanque.getCodeBanque());
        banqueForm.setContact(pbanque.getContactBanque());
        banqueForm.setFrontEndUrl(pbanque.getFrontEndUrl());
        banqueForm.setValidationFormUrl(pbanque.getValidationFormUrl());
        banqueForm.setWsPassword(pbanque.getWsPassword());
        banqueForm.setWsUsername(pbanque.getWsUsername());
        }
        return banqueForm;
    }

    private RapportForm remplirRapport(ParamAcces pParamAcces) {
        RapportForm rapportForm = new RapportForm();
        if(pParamAcces != null && pParamAcces.isRapporEnabled())  {
            rapportForm.setExternalEmailToSend(pParamAcces.getRapportParam().getParamRapportBeneficiaire());
            try{
                rapportForm.setFrequence(RapportForm.FREQUENCIES.valueOf(pParamAcces.getRapportParam().getParamRapportFrequence()));
            } catch (Exception e){
               LOG.log(Level.WARNING, "Valeur de l'attribut frequence provenant de la BDD non connu coté DTO : "+pParamAcces.getRapportParam().getParamRapportFrequence(), e);
            }
            try{
                rapportForm.setTypeTransaction(RapportForm.TRANSACTION_TYPE.valueOf(pParamAcces.getRapportParam().getParamRapportDonnees()));
            } catch (Exception e){
                LOG.log(Level.WARNING, "Valeur de l'attribut Transaction provenant de la BDD non connu coté DTO : "+pParamAcces.getRapportParam().getParamRapportDonnees(), e);
            }

            try{
                rapportForm.setFormat(RapportForm.FORMAT_TYPE.valueOf(pParamAcces.getRapportParam().getParamRapportFormat()));
            } catch (Exception e){
                LOG.log(Level.WARNING, "Valeur de l'attribut FORMAT Données provenant de la BDD non connu coté DTO : "+pParamAcces.getRapportParam().getParamRapportFormat(), e);
            }
            rapportForm.setIdRapport(pParamAcces.getRapportParam().getIdParamRapport().toString());
            rapportForm.setRepertoireRapport(pParamAcces.getRapportParam().getParamRapportRepertoire());
        }
        return rapportForm;
    }

    private MessagerieForm remplirMessagerieForm(ParamAcces pParamAcces) {

        MessagerieForm messagerieForm = new MessagerieForm();
        if(pParamAcces != null && pParamAcces.isSmtpEnabled())  {
        messagerieForm.setIdMessagerie(pParamAcces.getMessagerieParam().getIdParamSmtp().toString());
        messagerieForm.setEmail(pParamAcces.getMessagerieParam().getParamSmtpEmail());
        messagerieForm.setPassword(pParamAcces.getMessagerieParam().getParamSmtpPassword());
        messagerieForm.setUsername(pParamAcces.getMessagerieParam().getParamSmtpUsername());
        messagerieForm.setHost(pParamAcces.getMessagerieParam().getParamSmtpHost());
        messagerieForm.setPort(pParamAcces.getMessagerieParam().getParamSmtpPort());
            try{
                messagerieForm.setSecurite(MessagerieForm.SECURITY_TYPE.valueOf(pParamAcces.getMessagerieParam().getParamSmtpTransport()));
            } catch (Exception e){
                //
            }

        }
        return messagerieForm;
    }


    @RequestMapping(value = "settings/messagerie", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MessagerieForm submittedFromData(@RequestBody @Valid MessagerieForm messagerieForm, HttpServletRequest request, Model m) {

        if(banque == null){
            messagerieForm.setMsg("Vous devez renseigner une banque avant d'ajouter des parametres de messagerie");
            messagerieForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return  messagerieForm;
        }
        if (messagerieForm.getIdMessagerie() != null && messagerieForm.getIdMessagerie().trim().length()>0) {
            // update values
            if(banque.getParamAcces()==null)
                banque.setParamAcces(new ParamAcces());
            MessagerieParam messagerieParam = banque.getParamAcces().getMessagerieParam();
            messagerieParam.setParamSmtpEmail(messagerieForm.getEmail());
            messagerieParam.setParamSmtpHost(messagerieForm.getHost());
            messagerieParam.setParamSmtpUsername(messagerieForm.getUsername());
            messagerieParam.setParamSmtpPassword(messagerieForm.getPassword());
            messagerieParam.setParamSmtpPort(messagerieForm.getPort());
            messagerieParam.setParamSmtpTransport(messagerieForm.getSecurite().name());
            messagerieParam.setParams(banque.getParamAcces());
            metier.saveObject(messagerieParam);
            messagerieForm.setMsg("Les informations de la messagerie ont été mises à jour avec succès");
            messagerieForm.setResultat(BasicResponse.RETOUR_OK);
        } else{  // cas création

            if(banque.getParamAcces()==null)    {
                banque.setParamAcces(new ParamAcces());
            }
            MessagerieParam messagerieParam = new MessagerieParam();
            messagerieParam.setParamSmtpEmail(messagerieForm.getEmail());
            messagerieParam.setParamSmtpHost(messagerieForm.getHost());
            messagerieParam.setParamSmtpPassword(messagerieForm.getPassword());
             messagerieParam.setParamSmtpUsername(messagerieForm.getUsername());
            messagerieParam.setParamSmtpPort(messagerieForm.getPort());
            messagerieParam.setParamSmtpTransport(messagerieForm.getSecurite().name());

            banque.getParamAcces().setMessagerieParam(messagerieParam);
            banque= (Banque) metier.saveObject(banque);
            messagerieForm.setMsg("Les informations de la messagerie ont été ajoutées avec succès");
            messagerieForm.setResultat(BasicResponse.RETOUR_OK);
        }
         return  messagerieForm;

    }

    @RequestMapping(value = "settings/banque", method = RequestMethod.POST, consumes ="application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BanqueForm submittedBanqueData(@RequestBody @Valid BanqueForm banqueForm, HttpServletRequest request, Model m) {

        if (banqueForm.getIdBanque() != null && banqueForm.getIdBanque().trim().length()>0) {

            banque.setWsPassword(banqueForm.getWsPassword());
            banque.setValidationFormUrl(banqueForm.getValidationFormUrl());
            banque.setWsUsername(banqueForm.getWsUsername());
            banque.setFrontEndUrl(banqueForm.getFrontEndUrl());
            banque.setCigleBanque(banqueForm.getCigle());
            banque.setCodeBanque(banqueForm.getCodeBanque());
            banque.setContactBanque(banqueForm.getContact());
            banque.setWsEndpointUrl(banqueForm.getWsEndpointUrl());
            banque= (Banque)metier.saveObject(banque);

            banqueForm.setMsg("Les informations de la Banque ont été mises à jour avec succès");
            banqueForm.setResultat(BasicResponse.RETOUR_OK);
        } else{
            banque= new Banque();
            banque.setWsPassword(banqueForm.getWsPassword());
            banque.setValidationFormUrl(banqueForm.getValidationFormUrl());
            banque.setWsUsername(banqueForm.getWsUsername());
            banque.setFrontEndUrl(banqueForm.getFrontEndUrl());
            banque.setCigleBanque(banqueForm.getCigle());
            banque.setCodeBanque(banqueForm.getCodeBanque());
            banque.setContactBanque(banqueForm.getContact());
            banque.setWsEndpointUrl(banqueForm.getWsEndpointUrl());
            banque= (Banque)metier.saveObject(banque);
            banqueForm.setMsg("Les informations de la Banque ont été ajoutées avec succès");
            banqueForm.setResultat(BasicResponse.RETOUR_OK);
        }
        return  banqueForm;

    }

    @RequestMapping(value = "settings/rapport", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = "application/json")
    public @ResponseBody
    RapportForm submittedRapportData(@RequestBody @Valid RapportForm rapportForm, HttpServletRequest request,  Model m) {

        if(banque == null){
            rapportForm.setMsg("Vous devez renseigner une banque avant d'ajouter des parametres de messagerie");
            rapportForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return  rapportForm;
        }
        if (rapportForm.getIdRapport() != null && rapportForm.getIdRapport().trim().length()>0) {
            // update values
            if(banque.getParamAcces()==null)
                banque.setParamAcces(new ParamAcces());
            RapportParam param =banque.getParamAcces().getRapportParam();
            param.setParamRapportBeneficiaire(rapportForm.getExternalEmailToSend());

            param.setParamRapportDonnees(rapportForm.getTransactionValue());
            param.setParamRapportFormat(rapportForm.getFormatValue());
            param.setParamRapportFrequence(rapportForm.getFrequenceValue());

            param.setParamRapportRepertoire(rapportForm.getRepertoireRapport());
            banque.getParamAcces().setRapportParam(param);
            banque=((Banque) metier.saveObject(banque));
            rapportForm.setMsg("Les Parametres de rapport ont été mis à jour avec succès");
            rapportForm.setResultat(BasicResponse.RETOUR_OK);
        } else{
            if(banque.getParamAcces()==null)    {
                banque.setParamAcces(new ParamAcces());
            }
            RapportParam param =new RapportParam();

            param.setParamRapportBeneficiaire(rapportForm.getExternalEmailToSend());
            param.setParamRapportFrequence(rapportForm.getFrequenceValue());
            param.setParamRapportFormat(rapportForm.getFormatValue());
            param.setParamRapportDonnees(rapportForm.getTypeTransaction().name());
            param.setParamRapportRepertoire(rapportForm.getRepertoireRapport());
            banque.getParamAcces().setRapportParam(param);

           banque=(Banque) metier.saveObject(banque);

            rapportForm.setMsg("Les Parametres de rapport ont été ajoutés avec succès");
            rapportForm.setResultat(BasicResponse.RETOUR_OK);
        }
        return  rapportForm;

    }


}
