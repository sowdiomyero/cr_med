package fr.esecure.banking.mail.sender;

import fr.esecure.banking.modele.client.entities.Client;
import fr.esecure.banking.modele.client.entities.ParamAcces;
import fr.esecure.banking.modele.client.entities.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 23/02/15
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EmailSenderImpl implements  IEmailSender{

    private Session session;

/*    @Autowired
    private org.springframework.core.env.Environment env;*/

    @Override
    @Async
    public void notifyCustomer(Client client, ParamAcces params) {
        try {
            sendNotify(client, "Alerte sur une tentative d'utilisation de votre compte.", params);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendRapport(ParamAcces params, String filePath) {
       buildAndAttachFileToMessage(params,filePath);
    }

    public static enum TYPE_MAIL{
        ACCOUNT_CREATION,
        ACCOUNT_UPDATE,
        ACCOUNT_DESABLE,

    }

    Logger LOG = Logger.getLogger(EmailSenderImpl.class.getName());

    @Override
    @Async
    public void send(User user, String subject, String passwordGenerated, ParamAcces params) {

        LOG.log(Level.INFO, "Demande d'envoie de mail RECU pour le user : "+user.getUserMail());
        try {
            sendMessage(user,subject, TYPE_MAIL.ACCOUNT_UPDATE, passwordGenerated, params);
            LOG.log(Level.INFO, "Demande d'envoie de mail TERMINEE AVEC SUCCES : Envoyé à : "+user.getUserMail());
        } catch (MessagingException e) {
            e.printStackTrace();
            LOG.log(Level.SEVERE, "Echec envoi de mail au user : "+user.getUserMail(), e);
        }



    }

    @Override
    @Async
    public void sendAccountCreated(User user, String subject, String passwordGenerated, ParamAcces params) {
        LOG.log(Level.INFO, "CREATION COMPTE :: Préparation d'une Notification de mail au user : [ "+user.getUserMail()+" ] Avec le password generé suivant : "+passwordGenerated);
        try {
            sendMessage(user, subject, TYPE_MAIL.ACCOUNT_CREATION, passwordGenerated, params);
        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "CREATION COMPTE :: Echec envoi de mail au user : "+user.getUserMail()+" Avec le password generé suivant : "+passwordGenerated, e);
        }
    }

    @Override
    @Async
    public void sendAccountUpdated(User user, String subject, ParamAcces params) {
        try{
            sendMessage(user, subject, TYPE_MAIL.ACCOUNT_UPDATE, null, params);
        }
         catch (MessagingException e) {
             LOG.log(Level.SEVERE, "MISE A JOUR DE COMPTE :: Echec envoi de mail au user : "+user.getUserMail(), e);
        }

    }


    private void  sendMessage(User user,String object, TYPE_MAIL typeMail, String passwordGenerated, ParamAcces params) throws MessagingException {
        String status="MAIL_SENT_OK";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", params.getMessagerieParam().getParamSmtpHost());
        props.put("mail.smtp.port", params.getMessagerieParam().getParamSmtpPort());

        final String username = params.getMessagerieParam().getParamSmtpUsername();
        final String password = params.getMessagerieParam().getParamSmtpPassword();
         session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                 
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(params.getMessagerieParam().getParamSmtpEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserMail()));
            message.setSubject("[ESECURE]" + object, "UTF-8");

            message.setContent(buildHTMLText(user, passwordGenerated, typeMail), "text/html; charset=UTF-8");
            Transport.send(message);
            LOG.log(Level.INFO, "Mail Envoye avec succes apres transport");
        } catch (MessagingException e) {
            status="MAIL_SENT_NOK";
            LOG.log(Level.INFO, "Une exception pendant l'envoi du Mail : "+e.getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        }catch (Exception e) {
            status="MAIL_SENT_NOK";
            LOG.log(Level.SEVERE, "Une exception pendant l'envoi du Mail : "+e.getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        } finally {
            LOG.log(Level.SEVERE, "ENVOI DE MAIL : ETAT =  "+status);
        }

    }

    private String buildHTMLText(User user, String passwordGenerated, TYPE_MAIL typeMail){
        String message="<!DOCTYPE html>"+
                "<html>"+
                "<head>"+
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                "<style> "+
                "#myDIV {-moz-border-radius: 10px;"+
                "-webkit-border-radius: 10px;"+
                "border-radius: 10px;"+
                "width: 700px;"+
                "   height: 300px;"+
                // "   background-color:#BF245E;"+
                //"   background-color:#809999;"+
                //"   color: white;"+
                "   -webkit-animation: mymove 1s infinite;"+ /* Chrome, Safari, Opera */
                "   animation: mymove 5s infinite;"+
                "}"+

                /* Chrome, Safari, Opera */
                "@-webkit-keyframes mymove {"+
                "  50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+

                /* Standard syntax */
                "@keyframes mymove {"+
                "   50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+
                ".bigdiv {"+
                " width:100px;"+
                " height:100px;"+
                "}"+
                " .bigdiv img {"+
                "float:left;"+
                " }"+
                "</style>"+
                "</head>"+
                "<body BGCOLOR=white> <br/>"+
                //"<div class=bigdiv><img src=\"cid:\"/></div><br/> "+
                "<div id=myDIV>"+
                "<h3>Bonjour M. "+user.getUserName()+" "+user.getUserPrenom()+"\n,<br/> "+"\n<br/> "+
                    getMessage(user,passwordGenerated, typeMail)
                + "Bonne Reception.</h3>"+
                "</div> <br/><br/>"+
                " <br/>"+
                //  "<p align=left><img src=\"cid:"+cid+"\"/></p><br/>"+
                "</body>"+
                "</html>";
        return message;
    }

    private String getMessage(User user, String passwordGenerated, TYPE_MAIL typeMail){
        String response ="";
        switch (typeMail) {
            case ACCOUNT_CREATION:
                response += "  Votre compte a été crée avec succés."+"\n<br/> ";
            case ACCOUNT_UPDATE:
                response += "  Votre compte a été mis à jour avec succés."+"\n<br/> ";
            default:
                response += "  Une action a été ajoutée sur votre compte"+"\n<br/> ";
        }

        if(passwordGenerated != null && passwordGenerated.trim().length()>0){
         response += "  Vous trouverez ci dessous vos identifiants de connexion : <br/>"+"\n<br/> "+
                "  Login : "+user.getCompte().getLogin()+"\n<br/> "+
                "  Mot de passe : "+passwordGenerated  +"<br/>"+"\n<br/> ";
        }else{
         response += "  Des modifications ont été apportées sur votre profil ESECURE."+"\n<br/> "+
                     "  Vous devez vous logguer pour constater les modifications. Vous identifiants de connexion restent les mêmes.  <br/>"+"\n<br/> ";

        }
        return response;
    }
    
      private String buildHTMLTextNotify(Client client){
        String message="<!DOCTYPE html>"+
                "<html>"+
                "<head>"+
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                "<style> "+
                "#myDIV {-moz-border-radius: 10px;"+
                "-webkit-border-radius: 10px;"+
                "border-radius: 10px;"+
                "width: 700px;"+
                "   height: 300px;"+
                // "   background-color:#BF245E;"+
                "   background-color:#809999;"+
                "   color: white;"+
                "   -webkit-animation: mymove 1s infinite;"+ /* Chrome, Safari, Opera */
                "   animation: mymove 5s infinite;"+
                "}"+

                /* Chrome, Safari, Opera */
                "@-webkit-keyframes mymove {"+
                "  50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+

                /* Standard syntax */
                "@keyframes mymove {"+
                "   50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+
                ".bigdiv {"+
                " width:100px;"+
                " height:100px;"+
                "}"+
                " .bigdiv img {"+
                "float:left;"+
                " }"+
                "</style>"+
                "</head>"+
                "<body BGCOLOR=white> <br/>"+
                "<div class=bigdiv><img src=\"cid:\"/></div><br/> "+
                "<div id=myDIV>"+
                "<h3>Bonjour M. "+client.getNomClient()+" "+client.getPrenomClient()+"\n,<br/> "+"\n<br/> "+
                    getNotify()
                + "Bonne Reception</h3>"+
                "</div> <br/><br/>"+
                " <br/>"+
                //  "<p align=left><img src=\"cid:"+cid+"\"/></p><br/>"+
                "</body>"+
                "</html>";
        return message;
    }
    
    private String getNotify(){
        String response ="";
         response = "  Nous vous informons qu'une tentative de paiement d'une transaction avec votre carte bancaire a été détectée. Celle ci a été rejetée."+"\n,<br/> "+
                    "  Si vous etes à l'origine de cette action merci de contacter votre conseiller(e).  <br/>"+"\n<br/> ";
         return response;
    }
    
    private void  sendNotify(Client client, String object, ParamAcces params) throws MessagingException {
        //LOG.log(Level.INFO, "Delegue à la methode sendMessage ");
        //params= verifyParamAcces(params);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", params.getMessagerieParam().getParamSmtpHost());
        props.put("mail.smtp.port", params.getMessagerieParam().getParamSmtpPort());

        final String username = params.getMessagerieParam().getParamSmtpUsername();
        final String password = params.getMessagerieParam().getParamSmtpPassword();
         session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(params.getMessagerieParam().getParamSmtpEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(client.getEmail()));
            message.setSubject("[ESECURE]"+object, "UTF-8");

            message.setContent(buildHTMLTextNotify(client), "text/html; charset=UTF-8");
            Transport.send(message);
            LOG.log(Level.INFO, "Mail Envoye avec succes apres transport");
        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "Une exception pendant l'envoi du Mail : "+e.getCause().getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        } finally {

        }

    }



    private void buildAndAttachFileToMessage(ParamAcces params, String filePath){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", params.getMessagerieParam().getParamSmtpHost());
        props.put("mail.smtp.port", params.getMessagerieParam().getParamSmtpPort());

        final String username = params.getMessagerieParam().getParamSmtpUsername();
        final String password = params.getMessagerieParam().getParamSmtpPassword();
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(params.getMessagerieParam().getParamSmtpEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(params.getRapportParam().getParamRapportBeneficiaire()));
            message.setSubject("[ESECURED] Generation de Rapport ", "UTF-8");
            //message.setHeader("","");

            Multipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();

            // creation partie principale du message
            messageBodyPart.setContent(buildHTMLTextRapport(), "text/html; charset=UTF-8");

            multipart.addBodyPart(messageBodyPart);

            // creation et ajout de la piece jointe
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(source.getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            LOG.log(Level.INFO, "Mail Envoye avec succes apres transport : fichier attaché = "+filePath +" Adresse envoie du mail : "+params.getRapportParam().getParamRapportBeneficiaire());
        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "Une exception pendant l'envoi du Mail : "+e.getCause().getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        } finally {

        }

    }

    private String buildHTMLTextRapport(){
        String message="<!DOCTYPE html>"+
                "<html>"+
                "<head>"+
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                "<style> "+
                "#myDIV {-moz-border-radius: 10px;"+
                "-webkit-border-radius: 10px;"+
                "border-radius: 10px;"+
                "width: 700px;"+
                "   height: 300px;"+
                // "   background-color:#BF245E;"+
                //"   background-color:#809999;"+
                //"   color: white;"+
                "   -webkit-animation: mymove 1s infinite;"+ /* Chrome, Safari, Opera */
                "   animation: mymove 5s infinite;"+
                "}"+

                /* Chrome, Safari, Opera */
                "@-webkit-keyframes mymove {"+
                "  50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+

                /* Standard syntax */
                "@keyframes mymove {"+
                "   50% {box-shadow: 0px 20px 30px blue;}"+
                "}"+
                ".bigdiv {"+
                " width:100px;"+
                " height:100px;"+
                "}"+
                " .bigdiv img {"+
                "float:left;"+
                " }"+
                "</style>"+
                "</head>"+
                "<body BGCOLOR=white> <br/>"+
                //"<div class=bigdiv><img src=\"cid:\"/></div><br/> "+
                "<div id=myDIV>"+
                "<h3>Bonjour \n,<br/> "+"\n<br/> "
                +"  Veuillez recevoir en Pièce Jointe le rapport sur les transactions effectuées à travers la plateforme ESECURED <br/>"
                + " Cordialement,</h3>"
                + "Bonne Reception</h3>"+
                "</div> <br/><br/>"+
                " <br/>"+
                "</body>"+
                "</html>";
        return message;
    }



}
